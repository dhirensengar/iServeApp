package com.cears.serviceapp.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.Database.IncompleteDatabase;
import com.cears.serviceapp.Database.LocalDatabase;
import com.cears.serviceapp.Database.SubmitDatabase;
import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.Pojo.DataPojo;
import com.cears.serviceapp.Pojo.DatabasePojo;
import com.cears.serviceapp.Pojo.SubmitPojo;
import com.cears.serviceapp.R;
import com.cears.serviceapp.models.GetAllJob_Jobs;
import com.cears.serviceapp.models.QuestionSubResponse;
import com.cears.serviceapp.utils.ImageUtil;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

import static com.cears.serviceapp.Database.IncompleteDatabase.TABLE_LOCAL__DATA;
import static com.cears.serviceapp.Fragments.NewJobFragment.EQUIPMENT_NUMBER_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.JOB_TICKET_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_EQUIPMENT_TYPE;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_SERVICE_TYPE;
import static com.cears.serviceapp.GeneralHelpers.getEquipmentTypeValue;
import static com.cears.serviceapp.GeneralHelpers.getServiceTypeValue;
import static com.cears.serviceapp.GeneralHelpers.setIsSurveyInCompleted;
import static com.cears.serviceapp.R.id.layoutAnswerText;
import static com.cears.serviceapp.R.id.layoutButtonNextSurvey;
import static com.cears.serviceapp.R.id.layoutButtonPreviousSurvey;
import static com.cears.serviceapp.R.id.radioGroup;
import static com.cears.serviceapp.utils.ImageUtil.convert;


public class SurveyActivity extends BaseActivity {

    private TextView survey_textViewQuestions, mEquipmentNumberTextView, mServiceTypeTextView, mEquipmentTypeTextView, mQuestionTextView, mAdditionalAnswerField, mJobTicketNumberTextView, mQDMasterTextView;
    private LinearLayout layoutPreviousButton, layoutNextButton, layoutSubmitButton, layoutMarginLeft, layoutPhoto;
    private ImageView mPhotoImageView;
    private RadioGroup mRadioGroup, radioGroupUnit;
    private RadioButton mRadioButton1, mRadioButton2, mRadioButton3, mRadioButton4, mRadioButton5, mRadioButtonUnit1, mRadioButtonUnit2, mRadioButtonUnit3;
    private CheckBox mRadioButtonNA;
    private int servicePosition, equipmentTypePosition, apiCount = 0, apiCountTemp = 0, radioPosition = -1, RESULT_LOAD_IMAGE = 8888, REQUEST_IMAGE_CAPTURE = 999;
    private Button mClearButton, mSaveButton;
    private SignaturePad mSignaturePad;
    private ArrayList<DataPojo> dataList, dataListSorted, dataListTemp;

    private String[] equipmentTypeList, serviceList;
    private boolean isSkipped, isErrorred = false, isRadioButtonClicked = false;

    private String optionSelected = "", photo = "", photoString = "", pre_answer = "", equipmentType, serviceType, jobTicketNumber = "", EquipmentNumber = "", customerSignature = "", serviceIdTemp = "";
    private AlertDialog.Builder alert;
    private ProgressDialog progressDialog;
    String partNumber = "";
    String partQuantity = "";
    String partDescription = "";
    String manPower = "";
    String isSubmittedClicked = "", Q_type = "";
    String optionText = "";
    GeneralDatabase db1 = new GeneralDatabase(SurveyActivity.this);

    private int currentPositionQuestion = 0;
    private List<QuestionSubResponse> questionArrayList = new ArrayList<QuestionSubResponse>();
    private List<QuestionSubResponse> filteredQuestionList = new ArrayList<QuestionSubResponse>();

    public GetAllJob_Jobs maintainencejob_QuestAns = new GetAllJob_Jobs();

    GetAllJob_Jobs currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));

        init();

        Intent i = this.getIntent();
        if (i != null) {
            filteredQuestionList = (List<QuestionSubResponse>) i.getSerializableExtra("sectionlist");
            currentJob = (GetAllJob_Jobs) i.getSerializableExtra("job");
            Q_type = i.getStringExtra("Q_type");
        }

        if (filteredQuestionList == null || filteredQuestionList.size() == 0) {
            //   questionArrayList = db1.getQuestions();
            questionArrayList = filteredQuestionList;
        } else {
            questionArrayList = filteredQuestionList;
        }

        if (maintainencejob_QuestAns != null && currentJob != null && currentJob.getJobId() != null) {
            maintainencejob_QuestAns = db1.getAllJob_ByJobID(currentJob.getJobId());
        }

        if (maintainencejob_QuestAns != null && maintainencejob_QuestAns.getQuestionanslist() != null && maintainencejob_QuestAns.getQuestionanslist().size() > 0) {
            if (Q_type != null && Q_type.equals("Q"))
                questionArrayList = maintainencejob_QuestAns.getQuestionanslist();
        }

        if (Q_type != null && Q_type.equals("D")) {
            questionArrayList = filteredQuestionList;
        }

        if (Q_type != null && Q_type.equals("Q")) {
            mRadioGroup.setVisibility(View.VISIBLE);
            radioGroupUnit.setVisibility(View.GONE);
            findViewById(layoutAnswerText).setVisibility(View.GONE);
            if (questionArrayList.size() == 1) {
                survey_textViewQuestions.setText(questionArrayList.get(currentPositionQuestion).getQuestion() + "");
                mQDMasterTextView.setText(questionArrayList.get(currentPositionQuestion).getType() + "");
                SetRadioBtnText(currentPositionQuestion);
                mRadioGroup.setOnCheckedChangeListener(null);
                mRadioGroup.clearCheck();
                mRadioGroup.setOnCheckedChangeListener(CheckedChangeListener);
                findViewById(R.id.layoutSkip).setVisibility(View.GONE);
                findViewById(R.id.layoutButtonPreviousSurvey).setVisibility(View.GONE);
                findViewById(R.id.layoutButtonSubmitSurvey).setVisibility(View.VISIBLE);
                findViewById(R.id.layoutButtonNextSurvey).setVisibility(View.GONE);
            } else {
                showQuestion(currentPositionQuestion);
                layoutNextButton.setVisibility(View.VISIBLE);
                layoutSubmitButton.setVisibility(View.GONE);
                layoutPreviousButton.setVisibility(View.GONE);
                layoutMarginLeft.setVisibility(View.GONE);
            }
        } else if (Q_type != null && Q_type.equals("D")) {
            showQuestion(currentPositionQuestion);
        }


        jobTicketNumber = getIntent().getStringExtra(JOB_TICKET_CONSTANT);

        //set buttons visibility

        mRadioButtonNA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    radioGroupUnit.setVisibility(View.GONE);
                    mAdditionalAnswerField.setVisibility(View.GONE);
                } else {
                    if (!questionArrayList.get(currentPositionQuestion).getUnit().equals("")) {
                        radioGroupUnit.setVisibility(View.VISIBLE);
                    }
                    mAdditionalAnswerField.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    RadioGroup.OnCheckedChangeListener CheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            if (id != -1) {
                optionText = "";
                RadioButton selectedRadioButton = (RadioButton) findViewById(id);
                optionText = selectedRadioButton.getText().toString();
                optionSelected = optionText;
                if (optionText.equals("Done") || optionText.equals("All OK") || optionText.equals("Replaced") || optionText.equals("Repaired") || optionText.equals("To repair") || optionText.equals("To replace")) {
                  //  questionArrayList.get(currentPositionQuestion).setAnswer("" + optionText);
                    Log.e("Survey", "+Answer " + questionArrayList.get(currentPositionQuestion).getAnswer());
                    if (optionText.equalsIgnoreCase("Repaired") || optionText.equalsIgnoreCase("To Repair")) {
                    //    questionArrayList.get(currentPositionQuestion).setAnswer("" + optionText);
                        Log.e("Survey", "Answer " + questionArrayList.get(currentPositionQuestion).getAnswer());
                        showPopUpDialogForPartDetail();
                    }
                } else {
                    optionSelected = optionText;
                }
            }
        }
    };

    private void showQuestion(int position) {

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String startTime = df.format(today);

        Log.e("startTime", " " + startTime);
        questionArrayList.get(position).setStartDate(startTime);

        try {
            if (position > 0) {
                findViewById(R.id.layoutButtonPreviousSurvey).setVisibility(View.VISIBLE);
            }
            if (Q_type != null && Q_type.equals("D")) {
                mRadioGroup.setVisibility(View.GONE);
                findViewById(layoutAnswerText).setVisibility(View.VISIBLE);
                setDataToFields(position);

                if (!questionArrayList.get(position).getAnswer().equals("")) {
                    setRadioButtonChecked(position);
                }

            } else if (Q_type != null && Q_type.equals("Q")) {
                mRadioGroup.setVisibility(View.VISIBLE);
                radioGroupUnit.setVisibility(View.GONE);
                findViewById(layoutAnswerText).setVisibility(View.GONE);

                if (position < questionArrayList.size() - 1) {
                    if (questionArrayList.get(position).getAnswer().equals("")) {
                        survey_textViewQuestions.setText(+(position + 1) + " : " + questionArrayList.get(position).getQuestion() + "");
                        mQDMasterTextView.setText(questionArrayList.get(position).getType() + "");
                        SetRadioBtnText(position);
                        mRadioGroup.setOnCheckedChangeListener(null);
                        mRadioGroup.clearCheck();
                        mRadioGroup.setOnCheckedChangeListener(CheckedChangeListener);

                    } else if (!questionArrayList.get(position).getAnswer().equals("")) {
                        currentPositionQuestion++;
                        showQuestion(currentPositionQuestion);
                    }
                } else /*if (position == questionArrayList.size())*/ {

                    //           if (questionArrayList.get(position).getAnswer().equals("")) {
                    if (isAllAnswerGiven()) {
                        findViewById(R.id.layoutButtonPreviousSurvey).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutButtonSubmitSurvey).setVisibility(View.VISIBLE);
                        findViewById(R.id.layoutSkip).setVisibility(View.GONE);
                        findViewById(R.id.layoutButtonNextSurvey).setVisibility(View.GONE);
                    } else {
                        currentPositionQuestion = 0;
                        showQuestion(currentPositionQuestion);
                        currentPositionQuestion++;
                    }
                    // submit


                   /* } else if (!questionArrayList.get(position).getAnswer().equals("")) {
                        currentPositionQuestion++;
                        showQuestion(currentPositionQuestion);
                    }*/
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showPreviousQuestion(int position) {

        if (position > 0 && position <= questionArrayList.size()) {
            findViewById(R.id.layoutButtonSubmitSurvey).setVisibility(View.GONE);
            findViewById(R.id.layoutButtonNextSurvey).setVisibility(View.VISIBLE);
            survey_textViewQuestions.setText(+(position + 1) + " :" + questionArrayList.get(position).getQuestion() + "");
            mQDMasterTextView.setText(questionArrayList.get(position).getType() + "");
            SetRadioBtnText(position);
            mRadioGroup.setOnCheckedChangeListener(null);
            mRadioGroup.setOnCheckedChangeListener(CheckedChangeListener);
        } else if (position == 0) {
            findViewById(R.id.layoutButtonPreviousSurvey).setVisibility(View.GONE);
            findViewById(R.id.layoutButtonSubmitSurvey).setVisibility(View.GONE);
            findViewById(R.id.layoutButtonNextSurvey).setVisibility(View.VISIBLE);
            survey_textViewQuestions.setText(+(position + 1) + " :" + questionArrayList.get(position).getQuestion() + "");
            mQDMasterTextView.setText(questionArrayList.get(position).getType() + "");
            SetRadioBtnText(position);
            mRadioGroup.setOnCheckedChangeListener(null);
            mRadioGroup.setOnCheckedChangeListener(CheckedChangeListener);
        }
    }

    private boolean isAllAnswerSkipped() {
        for (int i = 0; i < questionArrayList.size(); i++) {
            if (questionArrayList.get(i).getSkipped().equals("yes")) {
                // QuestAnswer_list.get(i).getSkipped().equalsIgnoreCase("yes");
                return false;
            }
        }
        return true;
    }


    private boolean isAllAnswerGiven() {
        for (int i = 0; i < questionArrayList.size(); i++) {
            if (questionArrayList.get(i).getAnswer().equals("")) {
                // QuestAnswer_list.get(i).getSkipped().equalsIgnoreCase("yes");
                return false;
            }
        }
        return true;
    }

    private void SetRadioBtnText(int postion) {

        if (questionArrayList.get(postion).getR1() != null && !questionArrayList.get(postion).getR1().equals("")) {
            mRadioButton1.setVisibility(View.VISIBLE);
            mRadioButton1.setText(questionArrayList.get(postion).getR1());
        } else {
            mRadioButton1.setVisibility(View.GONE);
        }

        if (questionArrayList.get(postion).getR2() == null || questionArrayList.get(postion).getR2().equalsIgnoreCase("")) {
            mRadioButton2.setVisibility(View.GONE);
        } else {
            mRadioButton2.setVisibility(View.VISIBLE);
            mRadioButton2.setText(questionArrayList.get(postion).getR2());
        }

        if (questionArrayList.get(postion).getR3() == null || questionArrayList.get(postion).getR3().equalsIgnoreCase("")) {
            mRadioButton3.setVisibility(View.GONE);
        } else {
            mRadioButton3.setVisibility(View.VISIBLE);
            mRadioButton3.setText(questionArrayList.get(postion).getR3());
        }

        if (questionArrayList.get(postion).getR4() == null || questionArrayList.get(postion).getR4().equalsIgnoreCase("")) {
            mRadioButton4.setVisibility(View.GONE);
        } else {
            mRadioButton4.setVisibility(View.VISIBLE);
            mRadioButton4.setText(questionArrayList.get(postion).getR4());
        }

        if (questionArrayList.get(postion).getR5() == null || questionArrayList.get(postion).getR5().equalsIgnoreCase("")) {
            mRadioButton5.setVisibility(View.GONE);
        } else {
            mRadioButton5.setVisibility(View.VISIBLE);
            mRadioButton5.setText(questionArrayList.get(postion).getR5());
        }
    }


    public void onNextSurveyClick(View view) {

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String endTime = df.format(today);

        questionArrayList.get(currentPositionQuestion).setEndTime(endTime);

        Log.e("TAG", "onNextSurveyClick: " + currentPositionQuestion);

        if (Q_type != null && Q_type.equals("D")) {
            if (radioGroupUnit.getCheckedRadioButtonId() != -1) {
                questionArrayList.get(currentPositionQuestion).setSkipped("no");
                if (optionSelected!=null && !optionSelected.equals(""));
                questionArrayList.get(currentPositionQuestion).setAnswer("" + optionSelected);
                if (currentPositionQuestion < questionArrayList.size()) {
                    currentPositionQuestion = currentPositionQuestion + 1;
                    showQuestion(currentPositionQuestion);
                    //SetRadioBtnText(currentPositionQuestion);
                }
            } else {
                Toasty.info(getBaseContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (mRadioGroup.getCheckedRadioButtonId() != -1) {
                questionArrayList.get(currentPositionQuestion).setSkipped("no");
                if (currentPositionQuestion < questionArrayList.size()) {
                    currentPositionQuestion = currentPositionQuestion + 1;
                    showQuestion(currentPositionQuestion);
                    // SetRadioBtnText(currentPositionQuestion);
                }
            } else {
                Toasty.info(getBaseContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            }
        }

        // nextClick();
    }

    public void onSubmitSurveyClick(View view) {

        if (currentJob != null)
       //     db1.updateJobtype(currentJob.getJobId(), "submit");
        db1.close();
        finish();

        //   nextClick();
    }

    public void onPreviousClickSurvey(View view) {
        currentPositionQuestion--;
        if (currentPositionQuestion >= 0) {
            showPreviousQuestion(currentPositionQuestion);
        } else {
            findViewById(layoutButtonPreviousSurvey).setVisibility(View.GONE);
            currentPositionQuestion = 0;
        }
    }

    public void onSkipClick(View view) {
        questionArrayList.get(currentPositionQuestion).setSkipped("yes");
        if (currentPositionQuestion < questionArrayList.size()) {
            currentPositionQuestion = currentPositionQuestion + 1;
            showQuestion(currentPositionQuestion);
            mRadioGroup.clearCheck();
//            SetRadioBtnText(currentPositionQuestion);
        }

    }

    public void onTakePhotoClick(View view) {
        takePhoto();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_survey_page;
    }

    // Sorting operational data according to service  type and equipment type a

    private void sortData() {
        dataListSorted = new ArrayList<DataPojo>();

        for (int i = 0; i < dataList.size(); i++) {
            int optionCount = 0;
            DataPojo dbPojo = new DataPojo();
            if (getServiceTypeValue(servicePosition, i, dataList).equals("1") && getEquipmentTypeValue(equipmentTypePosition, i, dataList).equals("1")) {
                dbPojo.setId(dataList.get(i).getId());
                dbPojo.setGroup(dataList.get(i).getGroup());
                dbPojo.setQuestion(dataList.get(i).getQuestion());
                dbPojo.setQuestionType(dataList.get(i).getQuestionType());
                dbPojo.setAnswer("");
                dbPojo.setPartDescription("");
                dbPojo.setPartNumber("");
                dbPojo.setPartQuantity("");
                dbPojo.setManPower("");
                dbPojo.setIsRepaired("0");
                dbPojo.setPhotoLink("");
                dbPojo.setAdditionalText("");
                dbPojo.setOptionText("");
                if (!dataList.get(i).getUnit().equals("null")) {
                    dbPojo.setUnit("" + dataList.get(i).getUnit());
                    dbPojo.setUnitFixed("" + dataList.get(i).getUnit());
                } else {
                    dbPojo.setUnitFixed("");
                    dbPojo.setUnit("");
                }
                dbPojo.setCode(dataList.get(i).getCode());
                dbPojo.setAnswered(false);

                dbPojo.setCheckedPosition(-1);
                dbPojo.setPreAnswer("" + pre_answer);
                dbPojo.setDateString("");
                dbPojo.setIsPhoto(dataList.get(i).getIsPhoto());
                if (dataList.get(i).getQuestionType().equals("Q")) {

                    if (!dataList.get(i).getResponse1().equals("")) {
                        optionCount = optionCount + 1;
                        dbPojo.setOption1(dataList.get(i).getResponse1());
                    }

                    if (!dataList.get(i).getResponse2().equals("")) {
                        optionCount = optionCount + 1;
                        dbPojo.setOption2(dataList.get(i).getResponse2());
                    }

                    if (!dataList.get(i).getResponse3().equals("")) {
                        optionCount = optionCount + 1;
                        dbPojo.setOption3(dataList.get(i).getResponse3());
                    }

                    if (!dataList.get(i).getResponse4().equals("")) {
                        optionCount = optionCount + 1;
                        dbPojo.setOption4(dataList.get(i).getResponse4());
                    }

                    if (!dataList.get(i).getResponse5().equals("")) {
                        optionCount = optionCount + 1;
                        dbPojo.setOption5(dataList.get(i).getResponse5());
                    }

                } else {
                    dbPojo.setIsPhoto("0");
                }
                dbPojo.setOptionCount(optionCount);
                dataListSorted.add(dbPojo);
            }
        }
        for (int i = 0; i < dataListSorted.size(); i++) {
            dataListSorted.get(i).setQuestion(+(i + 1) + " : " + dataListSorted.get(i).getQuestion());
        }
    }

    private void setDataToFields(int position) {
        findViewById(R.id.layoutAllData).setVisibility(View.VISIBLE);
        Log.e("Survey Activity", "SetData");
        isRadioButtonClicked = false;
        mRadioButtonNA.setChecked(false);

        if (position == questionArrayList.size()) {
            position = position - 1;
        }

        //To handle input type only for two condition
        if (questionArrayList.size() > position) {
            changeInputTypeForSpecialCase(position, questionArrayList.get(position).getQuestion());
        }
        mAdditionalAnswerField.setText("");
        mAdditionalAnswerField.setError(null);
        radioGroupUnit.clearCheck();
        mRadioGroup.clearCheck();
        mPhotoImageView.setImageBitmap(null);
        survey_textViewQuestions.setText(+position + " :" + questionArrayList.get(position).getQuestion() + "");
        mQDMasterTextView.setText(questionArrayList.get(position).getType() + "");

        if (position == questionArrayList.size() - 1) {
            findViewById(R.id.layoutSkip).setVisibility(View.GONE);
        } else {
            findViewById(R.id.layoutSkip).setVisibility(View.VISIBLE);
        }

        //("Units fixed", " : - " + questionArrayList.get(position).getUnitFixed());

        if (!questionArrayList.get(position).getUnit().equals("")) {
            radioGroupUnit.setVisibility(View.VISIBLE);
            String[] parts = questionArrayList.get(position).getUnit().split(Pattern.quote("/"));

            setUnitRadioButtons(parts.length, parts);

            if (!questionArrayList.get(position).getAnswer().equals("")) {
                radioGroupUnit.clearCheck();
                setUnitRadioButtonChecked(position);
            }
        } else {
            radioGroupUnit.setVisibility(View.GONE);
        }

        if (questionArrayList.get(position).getPhoto().equals("1")) {
            findViewById(R.id.imageViewCamera).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.imageViewCamera).setVisibility(View.GONE);
        }

        if (position == 0) {
            layoutNextButton.setVisibility(View.VISIBLE);
            layoutMarginLeft.setVisibility(View.GONE);
            layoutSubmitButton.setVisibility(View.GONE);
            layoutPreviousButton.setVisibility(View.GONE);

            if (questionArrayList.size() == 1) {
                Log.e("Survey Activity", "1 SetData");
                layoutNextButton.setVisibility(View.GONE);
                layoutMarginLeft.setVisibility(View.GONE);
                layoutSubmitButton.setVisibility(View.VISIBLE);
                layoutPreviousButton.setVisibility(View.GONE);

//                ArrayList<DatabasePojo> techNameList = new ArrayList<DatabasePojo>();
//                NewGeneralDatabase generalDb = new NewGeneralDatabase(this);
//                techNameList = generalDb.getGeneralData(jobTicketNumber);
//
//                if (techNameList.size() > 0) {
//                    if (!techNameList.get(0).getSignatureLink().equals("") && GeneralHelpers.isSurveyInCompleted(this)) {
//                        findViewById(R.id.layoutAllData).setVisibility(View.INVISIBLE);
//                        showSubmitPopup();
//                    }
//                }


            }

        } else if (position == questionArrayList.size() - 1) {
            layoutMarginLeft.setVisibility(View.VISIBLE);
            layoutSubmitButton.setVisibility(View.VISIBLE);
            layoutPreviousButton.setVisibility(View.VISIBLE);
            layoutNextButton.setVisibility(View.GONE);
            //("Survey Activity", " is submitted  : " + isSubmittedClicked.equals("1"));

            if (isSubmittedClicked.equals("1")) {
                Log.e("Survey Activity", " 2 SetData");
                layoutNextButton.setVisibility(View.GONE);
                layoutMarginLeft.setVisibility(View.GONE);
                layoutSubmitButton.setVisibility(View.VISIBLE);
                layoutPreviousButton.setVisibility(View.GONE);
            }

        } else {
            layoutNextButton.setVisibility(View.VISIBLE);
            layoutMarginLeft.setVisibility(View.VISIBLE);
            layoutSubmitButton.setVisibility(View.GONE);
            layoutPreviousButton.setVisibility(View.VISIBLE);
        }


        if (isSubmittedClicked.equals("1")) {

        }
        if (!questionArrayList.get(position).getPhoto().equals("1")) {
            layoutPhoto.setVisibility(View.GONE);
        } else {
            layoutPhoto.setVisibility(View.VISIBLE);

           /* if (!questionArrayList.get(position).getPhotoLink().equals("")) {
                mPhotoImageView.setImageBitmap(null);
                mPhotoImageView.setImageBitmap(convert(questionArrayList.get(position).getPhotoLink()));
            }*/
        }

        if (questionArrayList.get(position).getType().equals("Q")) {
            mRadioGroup.setVisibility(View.VISIBLE);
            findViewById(layoutAnswerText).setVisibility(View.GONE);
            invisibleAllRadioButtons();
            setRadioButtonChecked(position);
            // setDataToRadioButtons(questionArrayList.get(position).getOptionCount(), questionArrayList, position);

            if (!questionArrayList.get(position).getAnswer().equals("")) {
                setRadioButtonChecked(position);
            }

        } else {
            mRadioGroup.setVisibility(View.GONE);
            findViewById(layoutAnswerText).setVisibility(View.VISIBLE);
            if (questionArrayList.get(position).getAnswer().equals("")) {
                mAdditionalAnswerField.setText(questionArrayList.get(position).getAdditionalText());
            }
        }
        //to handle radio button double  click exception
        isRadioButtonClicked = true;
        isSkipped = false;
    }

    private void setRadioButtonChecked(int position) {

        //
        if (questionArrayList.get(position).getCheckedPosition() == 0) {
            mRadioButton1.setChecked(true);
        } else if (questionArrayList.get(position).getCheckedPosition() == 1) {
            mRadioButton2.setChecked(true);
        } else if (questionArrayList.get(position).getCheckedPosition() == 2) {
            mRadioButton3.setChecked(true);
        } else if (questionArrayList.get(position).getCheckedPosition() == 3) {
            mRadioButton4.setChecked(true);
        } else if (questionArrayList.get(position).getCheckedPosition() == 4) {
            mRadioButton5.setChecked(true);
        }
    }

    private void setUnitRadioButtonChecked(int position) {
//        radioGroupUnit.clearCheck();
        if (questionArrayList.get(position).getCheckedPosition() == 0) {
            mRadioButtonUnit1.setChecked(true);
        } else if (questionArrayList.get(position).getCheckedPosition() == 1) {
            mRadioButtonUnit2.setChecked(true);
        } else if (questionArrayList.get(position).getCheckedPosition() == 2) {
            mRadioButtonUnit3.setChecked(true);
        }
    }

    private void setDataToRadioButtons(int count, ArrayList<DataPojo> data, int dataPosition) {
        if (count == 1) {
            mRadioButton1.setVisibility(View.VISIBLE);
            mRadioButton1.setText(data.get(dataPosition).getOption1());
        } else if (count == 2) {
            mRadioButton1.setVisibility(View.VISIBLE);
            mRadioButton2.setVisibility(View.VISIBLE);

            mRadioButton1.setText(data.get(dataPosition).getOption1());
            mRadioButton2.setText(data.get(dataPosition).getOption2());
        } else if (count == 3) {
            mRadioButton1.setVisibility(View.VISIBLE);
            mRadioButton2.setVisibility(View.VISIBLE);
            mRadioButton3.setVisibility(View.VISIBLE);
            mRadioButton1.setText(data.get(dataPosition).getOption1());
            mRadioButton2.setText(data.get(dataPosition).getOption2());
            mRadioButton3.setText(data.get(dataPosition).getOption3());

        } else if (count == 4) {
            mRadioButton1.setVisibility(View.VISIBLE);
            mRadioButton2.setVisibility(View.VISIBLE);
            mRadioButton3.setVisibility(View.VISIBLE);
            mRadioButton4.setVisibility(View.VISIBLE);

            mRadioButton1.setText(data.get(dataPosition).getOption1());
            mRadioButton2.setText(data.get(dataPosition).getOption2());
            mRadioButton3.setText(data.get(dataPosition).getOption3());
            mRadioButton4.setText(data.get(dataPosition).getOption4());
        } else if (count == 5) {
            mRadioButton1.setVisibility(View.VISIBLE);
            mRadioButton2.setVisibility(View.VISIBLE);
            mRadioButton3.setVisibility(View.VISIBLE);
            mRadioButton4.setVisibility(View.VISIBLE);
            mRadioButton5.setVisibility(View.VISIBLE);

            mRadioButton1.setText(data.get(dataPosition).getOption1());
            mRadioButton2.setText(data.get(dataPosition).getOption2());
            mRadioButton3.setText(data.get(dataPosition).getOption3());
            mRadioButton4.setText(data.get(dataPosition).getOption4());
            mRadioButton5.setText(data.get(dataPosition).getOption5());

        }
    }

    private void nextClick() {
        //("OnNext Click", "data size = " + questionArrayList.size() + " current position = " + currentPositionQuestion);
        if (dataListSorted.size() - 1 >= currentPositionQuestion) {

            if (dataListSorted.get(currentPositionQuestion).getQuestionType().equals("Q")) {
                //checking buttons selected or not
                //("OnNext Click", "Entered Q type");
                if (mRadioGroup.getCheckedRadioButtonId() != -1) {
                    //("OnNext Click", "RAdio Button Selecetd");
                    optionSelected = "";
                    String photo = "";
                    radioPosition = -1;
                    if (mRadioGroup.getCheckedRadioButtonId() == R.id.radioButton1) {
                        radioPosition = 0;
                        optionSelected = mRadioButton1.getText().toString();
                    } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.radioButton2) {
                        radioPosition = 1;
                        optionSelected = mRadioButton2.getText().toString();
                    } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                        radioPosition = 2;
                        optionSelected = mRadioButton3.getText().toString();
                    } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.radioButton4) {
                        radioPosition = 3;
                        optionSelected = mRadioButton4.getText().toString();
                    } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.radioButton5) {
                        radioPosition = 4;
                        optionSelected = mRadioButton5.getText().toString();
                    }
                    photo = dataListSorted.get(currentPositionQuestion).getPhotoLink();
                    this.photo = photo;


                    if ((dataListSorted.get(currentPositionQuestion).getIsPhoto().equals("0") || !dataListSorted.get(currentPositionQuestion).getPhotoLink().equals(""))) {

                        if (!isSkipped) {

                            if (dataListSorted.get(currentPositionQuestion).getAnswer().equals("")) {
                                dataListSorted.get(currentPositionQuestion).setCheckedPosition(radioPosition);
                                //("OnNext Click", "Answered");
                                if (optionSelected.equals("Replaced") || optionSelected.equals("Repaired") || optionSelected.equals("To repair") || optionSelected.equals("To replace")) {
                                    if (optionSelected.equals("Repaired") || optionSelected.equals("Replaced")) {
                                        dataListSorted.get(currentPositionQuestion).setIsRepaired("1");
                                    } else {
                                        dataListSorted.get(currentPositionQuestion).setIsRepaired("0");
                                    }

                                } else {
                                    dataListSorted.get(currentPositionQuestion).setIsRepaired("0");
                                    dataListSorted.get(currentPositionQuestion).setPartNumber("");
                                    dataListSorted.get(currentPositionQuestion).setPartQuantity("");
                                    dataListSorted.get(currentPositionQuestion).setPartDescription("");
                                    dataListSorted.get(currentPositionQuestion).setManPower("");
                                    partNumber = "";
                                    partDescription = "";
                                    partQuantity = "";
                                    manPower = "";
                                    dataListSorted.get(currentPositionQuestion).setIsRepaired("0");

                                }
                                LocalDatabase db = new LocalDatabase(this);
                                db.updateSavedData(TABLE_LOCAL__DATA, jobTicketNumber, dataListSorted.get(currentPositionQuestion).getId(), "", "" + photo, optionSelected, partNumber, partDescription, partQuantity, manPower, "0");
                                db.close();

                            } else {
                                //("OnNext Click", "Not Answered");
                                dataListSorted.get(currentPositionQuestion).setCheckedPosition(radioPosition);

                                if (optionSelected.equals("Replaced") || optionSelected.equals("Repaired") || optionSelected.equals("To repair") || optionSelected.equals("To replace")) {
                                    if (optionSelected.equals("Repaired") || optionSelected.equals("Replaced")) {
                                        dataListSorted.get(currentPositionQuestion).setIsRepaired("1");

                                    } else {
                                        dataListSorted.get(currentPositionQuestion).setIsRepaired("0");
                                    }


                                } else {

                                    dataListSorted.get(currentPositionQuestion).setIsRepaired("0");
                                    dataListSorted.get(currentPositionQuestion).setPartNumber("");
                                    dataListSorted.get(currentPositionQuestion).setPartQuantity("");
                                    dataListSorted.get(currentPositionQuestion).setPartDescription("");
                                    dataListSorted.get(currentPositionQuestion).setManPower("");

                                }
                                saveData(photo, "", optionSelected);
                            }

                        }

                        //To hide skip button in last position


                        if (!isSkipped) {
                            dataListSorted.get(currentPositionQuestion).setOptionText("" + optionSelected);
                            dataListSorted.get(currentPositionQuestion).setPhotoLink("" + photo);
                            currentPositionQuestion = currentPositionQuestion + 1;
                        }

                        if (dataListSorted.size() - 1 == currentPositionQuestion) {
                            findViewById(R.id.layoutSkip).setVisibility(View.GONE);
                        } else {
                            findViewById(R.id.layoutSkip).setVisibility(View.VISIBLE);
                        }
                        //("Is isRepairSelected", "false");
//                           //
                        radioGroupUnit.clearCheck();
                        if (currentPositionQuestion != dataListSorted.size()) {
                            setDataToFields(currentPositionQuestion);
                        } else {
                            submitData();
                        }

                    } else {

                        Log.e("Survey Activity", " link : " + dataListSorted.get(currentPositionQuestion).getPhotoLink());           // todo
                        Log.e("Survey Activity", " is photo : " + dataListSorted.get(currentPositionQuestion).getIsPhoto());
                        GeneralHelpers.showPopUp(getBaseContext(), "Please upload image first.");
                    }
                } else {
                    //("OnNext Click", "RAdio Button Not Selecetd");
                    //Checking for skip click if Yes then skip msg for null radio selection
                    if (isSkipped) {
                        //("OnNext Click", "RAdio Button Not Selecetd !! IS SKIPPED TRUE");
                        //
                        radioGroupUnit.clearCheck();
                        if (currentPositionQuestion != dataListSorted.size()) {
                            setDataToFields(currentPositionQuestion);
                        } else {
                            submitData();
                        }
                        isSkipped = false;
                    } else {
                        GeneralHelpers.showPopUp(getBaseContext(), "Please select an option");
                    }
                }

            } else {
                //("OnNext Click", "D type");
                if (mRadioButtonNA.isChecked() == true) {
                    mRadioButtonNA.setChecked(false);
                    mAdditionalAnswerField.setText("");

                    radioGroupUnit.clearCheck();
                    currentPositionQuestion = currentPositionQuestion + 1;
                    if (currentPositionQuestion != dataListSorted.size()) {
                        setDataToFields(currentPositionQuestion);
                    } else {
                        submitData();
                    }

//                    saveData("", "" + "", "", "", "", "", "", "");

                } else if (isSkipped) {
                    if (currentPositionQuestion != dataListSorted.size()) {
                        setDataToFields(currentPositionQuestion);
                    } else {
                        submitData();
                    }

                } else if (TextUtils.isEmpty(mAdditionalAnswerField.getText().toString().trim())) {
                    mRadioButtonNA.setChecked(false);
                    mAdditionalAnswerField.setError("Required Field");
                } else {
                    mAdditionalAnswerField.setError(null);
                    dataListSorted.get(currentPositionQuestion).setAnswer(mAdditionalAnswerField.getText().toString().trim());

                    if (!isSkipped) {

                        if (!dataListSorted.get(currentPositionQuestion).getAnswer().equals("")) {
                            LocalDatabase db = new LocalDatabase(this);

                            db.updateSavedData(TABLE_LOCAL__DATA, jobTicketNumber, dataListSorted.get(currentPositionQuestion).getId(), "" + mAdditionalAnswerField.getText().toString().trim(), "", "", "", "", "", "", "0");
                            currentPositionQuestion = currentPositionQuestion + 1;

                            //To hide skip button in last position
                            if (dataListSorted.size() - 1 == currentPositionQuestion) {
                                findViewById(R.id.layoutSkip).setVisibility(View.GONE);
                            } else {
                                findViewById(R.id.layoutSkip).setVisibility(View.VISIBLE);
                            }

                            mAdditionalAnswerField.setText("");

                            //("Checked id", " : " + " Answered");
                            if (currentPositionQuestion != dataListSorted.size()) {
                                setDataToFields(currentPositionQuestion);
                            } else {
                                submitData();
                            }
                        } else {
                            dataListSorted.get(currentPositionQuestion).setAdditionalText("" + mAdditionalAnswerField.getText().toString().trim());
                            if (!dataListSorted.get(currentPositionQuestion).getUnit().equals("")) {
                                //("Checked id", " : " + "Not Answered");
                                if (radioGroupUnit.getCheckedRadioButtonId() != -1) {


                                    if (radioGroupUnit.getCheckedRadioButtonId() == R.id.radioButtonUnit1) {
                                        radioPosition = 0;
                                        optionSelected = mRadioButtonUnit1.getText().toString();
                                    } else if (radioGroupUnit.getCheckedRadioButtonId() == R.id.radioButtonUnit2) {
                                        radioPosition = 1;
                                        optionSelected = mRadioButtonUnit2.getText().toString();
                                    } else if (radioGroupUnit.getCheckedRadioButtonId() == R.id.radioButtonUnit3) {
                                        radioPosition = 2;
                                        optionSelected = mRadioButtonUnit3.getText().toString();
                                    }
                                    dataListSorted.get(currentPositionQuestion).setUnit(optionSelected);
                                    dataListSorted.get(currentPositionQuestion).setCheckedPosition(radioPosition);

                                    saveData(photo, "" + mAdditionalAnswerField.getText().toString().trim(), "");
                                    currentPositionQuestion = currentPositionQuestion + 1;

                                    //To hide skip button in last position
                                    if (dataListSorted.size() - 1 == currentPositionQuestion) {
                                        findViewById(R.id.layoutSkip).setVisibility(View.GONE);
                                    } else {
                                        findViewById(R.id.layoutSkip).setVisibility(View.VISIBLE);
                                    }
                                    mAdditionalAnswerField.setText("");
                                    //
                                    if (currentPositionQuestion != dataListSorted.size()) {
                                        setDataToFields(currentPositionQuestion);
                                    } else {
                                        submitData();
                                    }


                                } else {

                                    GeneralHelpers.showPopUp(getBaseContext(), "Please select unit first");
                                }


                            } else {
                                saveData(photo, "" + mAdditionalAnswerField.getText().toString().trim(), "");
                                currentPositionQuestion = currentPositionQuestion + 1;

                                //To hide skip button in last position
                                if (dataListSorted.size() - 1 == currentPositionQuestion) {
                                    findViewById(R.id.layoutSkip).setVisibility(View.GONE);
                                } else {
                                    findViewById(R.id.layoutSkip).setVisibility(View.VISIBLE);
                                }
                                mAdditionalAnswerField.setText("");
                                //
                                if (currentPositionQuestion != dataListSorted.size()) {
                                    setDataToFields(currentPositionQuestion);
                                } else {
                                    submitData();
                                }
                            }
                        }

                    } else {

                    }
                }
            }

        } else {
            //("Survey Activity", "Greater than size");
            submitData();

        }

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(SurveyActivity.this, R.style.Dialog));
        alert.setTitle("Task is going to cancel");
        alert.setMessage("Are you sure?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setIsSurveyInCompleted(SurveyActivity.this, false);
                SurveyActivity.super.onBackPressed();
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.edit:
                AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Dialog));
                alert.setTitle("Data will be saved locally");
                alert.setNegativeButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog waitDialog = new ProgressDialog(SurveyActivity.this);
                        dialog.dismiss();
                        waitDialog.setMessage("Please wait..");
                        waitDialog.setCancelable(true);

                        if (currentJob != null) {
                            String QuestAnswer_Str = new Gson().toJson(questionArrayList);
                            db1.updateAns(currentJob.getJobId(), QuestAnswer_Str);
                            Log.e("Survey Inner Questions ", "" + QuestAnswer_Str + "jobTicketNumber " + currentJob.getJobId());
                        }

                        if (currentJob != null)
                            db1.updateJobtype(currentJob.getJobId(), "save");
                        db1.close();
                        setResult(Activity.RESULT_OK);
                        //GeneralHelpers.setCurrentFragment(SurveyActivity.this, 1);
                        waitDialog.show();
                        finish();
                    }
                });
                alert.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                alert.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
//        int width = displaymetrics.widthPixels;
        int height = 512;
        int width = 512;
        //("onActivityResult", "1");
//        if (requestCode == REQUEST_CODE_SAVED_FRAGMENT) {
//            GeneralHelpers.setIsSurveyInCompleted(this, true);
//        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && null != data) {
            try {


                Bitmap photo = (Bitmap) data.getExtras().get("data");
                photo = ImageUtil.scaleBitmap(photo, width, height);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 20, stream);
                byte[] imageInByte = stream.toByteArray();
                int length = imageInByte.length / 1024;

                //("SurveyActivity", "length : " + length);
                //convert image to byte array using base64
                this.photoString = convert(photo);

//                dataListSorted.get(currentPositionQuestion).setPhotoLink("" + photoString);

                stream.flush();
                mPhotoImageView.setImageBitmap(null);
                mPhotoImageView.setImageBitmap(photo);

            } catch (Exception e) {
                //("Survey", "Error on Camera Click");
                e.printStackTrace();
            }
        }
        //("onActivityResult", "2");
        //Sending for custom cropping after pick image from gallery
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            try {
                Uri selectedImage = data.getData();

                CropImage.activity(selectedImage)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            } catch (Exception e) {
                //("Survey", "Error on Custom Cropping");
                e.printStackTrace();
            }
        }
        //("onActivityResult", "3");
        //After cropping result
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    Uri resultUri = result.getUri();

                    InputStream image_stream = getContentResolver().openInputStream(resultUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(image_stream);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap = ImageUtil.scaleBitmap(bitmap, width, height);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 20, stream);
                    byte[] imageInByte = stream.toByteArray();
                    int length = imageInByte.length / 1024;

                    //("SurveyActivity", "length : " + length);

                    this.photoString = convert(bitmap);

                    //        dataListSorted.get(currentPositionQuestion).setPhotoLink("" + photoString);
                    stream.flush();

                    mPhotoImageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    //("Survey", "Error on After Cropping");
                    e.printStackTrace();

                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        GeneralHelpers.setIsSurveyInCompleted(this, false);
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //("OnResume ", "survey");
    }

    private void init() {

        jobTicketNumber = getIntent().getStringExtra(JOB_TICKET_CONSTANT);
        EquipmentNumber = getIntent().getStringExtra(EQUIPMENT_NUMBER_CONSTANT);
        serviceType = getIntent().getStringExtra(SELECTED_SERVICE_TYPE);
        equipmentType = getIntent().getStringExtra(SELECTED_EQUIPMENT_TYPE);


        survey_textViewQuestions = (TextView) findViewById(R.id.survey_textViewQuestions);
        mEquipmentNumberTextView = (TextView) findViewById(R.id.textViewEquipmentNumber);
        mServiceTypeTextView = (TextView) findViewById(R.id.textViewServiceType);
        mEquipmentTypeTextView = (TextView) findViewById(R.id.textViewEquipmentType);
        mAdditionalAnswerField = (TextView) findViewById(R.id.editTextAnswerAdditionalText);
        layoutPhoto = (LinearLayout) findViewById(R.id.layoutPhoto);
        mJobTicketNumberTextView = (TextView) findViewById(R.id.textViewJobTicketNumber);
        mQDMasterTextView = (TextView) findViewById(R.id.textViewQDMaster);
        //Radio Button
        mRadioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        mRadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        mRadioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        mRadioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        mRadioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        mRadioButtonUnit1 = (RadioButton) findViewById(R.id.radioButtonUnit1);
        mRadioButtonUnit2 = (RadioButton) findViewById(R.id.radioButtonUnit2);
        mRadioButtonUnit3 = (RadioButton) findViewById(R.id.radioButtonUnit3);
        mRadioButtonNA = (CheckBox) findViewById(R.id.radioButtonNA);

        mRadioGroup = (RadioGroup) findViewById(radioGroup);
        radioGroupUnit = (RadioGroup) findViewById(R.id.radioGroupUnit);
        mPhotoImageView = (ImageView) findViewById(R.id.imageViewPhoto);
        //layouts
        layoutNextButton = (LinearLayout) findViewById(layoutButtonNextSurvey);
        layoutPreviousButton = (LinearLayout) findViewById(R.id.layoutButtonPreviousSurvey);
        layoutSubmitButton = (LinearLayout) findViewById(R.id.layoutButtonSubmitSurvey);
        layoutMarginLeft = (LinearLayout) findViewById(R.id.layoutMarginLeftSurvey);

        equipmentTypeList = getResources().getStringArray(R.array.equipment_list);
        serviceList = getResources().getStringArray(R.array.service_list);

        mEquipmentNumberTextView.setText("Equipment Number  :  " + EquipmentNumber);
        mServiceTypeTextView.setText("Maintenance Type  :  " + serviceType);
        mEquipmentTypeTextView.setText("Equipment  :  " + equipmentType);
        mJobTicketNumberTextView.setText("Job Ticket Number  :  " + jobTicketNumber);
        progressDialog = new ProgressDialog(SurveyActivity.this);
        isSkipped = false;

    }

    private void invisibleAllRadioButtons() {
        mRadioButton1.setVisibility(View.GONE);
        mRadioButton2.setVisibility(View.GONE);
        mRadioButton3.setVisibility(View.GONE);
        mRadioButton4.setVisibility(View.GONE);
        mRadioButton5.setVisibility(View.GONE);
    }

    private void setUnitRadioButtons(int count, String[] units) {
        if (count == 0) {
            radioGroupUnit.setVisibility(View.GONE);
        } else if (count == 1) {
            radioGroupUnit.setVisibility(View.VISIBLE);
            mRadioButtonUnit1.setVisibility(View.VISIBLE);
            mRadioButtonUnit2.setVisibility(View.GONE);
            mRadioButtonUnit3.setVisibility(View.GONE);
            mRadioButtonUnit1.setText(units[0]);

        } else if (count == 2) {

            radioGroupUnit.setVisibility(View.VISIBLE);
            mRadioButtonUnit1.setVisibility(View.VISIBLE);
            mRadioButtonUnit2.setVisibility(View.VISIBLE);
            mRadioButtonUnit3.setVisibility(View.GONE);
            mRadioButtonUnit1.setText(units[0]);
            mRadioButtonUnit2.setText(units[1]);

        } else if (count == 3) {

            radioGroupUnit.setVisibility(View.VISIBLE);
            mRadioButtonUnit1.setVisibility(View.VISIBLE);
            mRadioButtonUnit2.setVisibility(View.VISIBLE);
            mRadioButtonUnit3.setVisibility(View.VISIBLE);
            mRadioButtonUnit1.setText(units[0]);
            mRadioButtonUnit2.setText(units[1]);
            mRadioButtonUnit3.setText(units[2]);

        }
    }

    // save data in local db in every next button click
    private void saveData(String photoLink, String additionalText, String allOption) {

        dataListSorted.get(currentPositionQuestion).setAnswered(true);
        LocalDatabase db = new LocalDatabase(this);
        DatabasePojo dbPojo = new DatabasePojo();
        dbPojo.setQuestion("" + dataListSorted.get(currentPositionQuestion).getQuestion());
        dbPojo.setQuestionType("" + dataListSorted.get(currentPositionQuestion).getQuestionType());
        dbPojo.setIsPhoto("" + dataListSorted.get(currentPositionQuestion).getIsPhoto());
        dbPojo.setId("" + dataListSorted.get(currentPositionQuestion).getId());
        dbPojo.setOptionCount(dataListSorted.get(currentPositionQuestion).getOptionCount());
        dbPojo.setGroup("" + dataListSorted.get(currentPositionQuestion).getGroup());
        dbPojo.setJobTicketNumber("" + getIntent().getStringExtra(JOB_TICKET_CONSTANT));
        dbPojo.setServiceType("" + serviceList[servicePosition]);
        dbPojo.setEquipmentType("" + equipmentTypeList[equipmentTypePosition]);
        dbPojo.setEquipNumber("" + EquipmentNumber);
        dbPojo.setOptionText("" + allOption);
        dbPojo.setPreAnswer("" + dataListSorted.get(0).getPreAnswer());
        dbPojo.setAdditionalTextFiled("" + additionalText);
        dbPojo.setPhotoLink("" + photoLink);
        dbPojo.setDateString("" + GeneralHelpers.currentDate(new Date()));

        //("saveData", "Unit Fixed: " + dataListSorted.get(currentPositionQuestion).getUnitFixed());
        if (!dataListSorted.get(currentPositionQuestion).getUnitFixed().equals("null")) {
            dbPojo.setUnitFixed("" + dataListSorted.get(currentPositionQuestion).getUnitFixed());
        } else {
            dbPojo.setUnitFixed("");
        }
        dbPojo.setUnit("" + dataListSorted.get(currentPositionQuestion).getUnit());
        if (!dataListSorted.get(currentPositionQuestion).getPartDescription().equals("null")) {
            dbPojo.setPartDescription("" + dataListSorted.get(currentPositionQuestion).getPartDescription());
        } else {
            dbPojo.setPartDescription("");
        }

        if (!dataListSorted.get(currentPositionQuestion).getPartNumber().equals("null")) {
            dbPojo.setPartNumber("" + dataListSorted.get(currentPositionQuestion).getPartNumber());
        } else {
            dbPojo.setPartNumber("");
        }
        if (!dataListSorted.get(currentPositionQuestion).getPartQuantity().equals("null")) {
            dbPojo.setPartQuantity("" + dataListSorted.get(currentPositionQuestion).getPartQuantity());
        } else {
            dbPojo.setPartQuantity("");
        }
        if (!dataListSorted.get(currentPositionQuestion).getManPower().equals("null")) {
            dbPojo.setManPower("" + dataListSorted.get(currentPositionQuestion).getManPower());
        } else {
            dbPojo.setManPower("");
        }
        if (!dataListSorted.get(currentPositionQuestion).getIsRepaired().equals("null")) {
            dbPojo.setIsRepaired("" + dataListSorted.get(currentPositionQuestion).getIsRepaired());
        } else {
            dbPojo.setIsRepaired("0");
        }


        db.addSavedDataToDb(dbPojo);
        db.close();
    }

    private void takePhoto() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SurveyActivity.this);
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    if (i == 0) {
                        Intent ii = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(ii, RESULT_LOAD_IMAGE);
                    } else {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                    }
                    if (dialogInterface != null) {
                        dialogInterface.dismiss();
                        dialogInterface.cancel();
                    }
                } catch (Exception e) {
                    //("Survey", "Error on Take Photo");
                    e.printStackTrace();
                }
            }
        });

        builder.show();
    }

    /**/
// save data after submission mode
    private void saveSubmitDataToDB() {
        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        LocalDatabase localDB = new LocalDatabase(this);
        SubmitDatabase submitDB = new SubmitDatabase(this);

        dataList = localDB.getAllLocalData(jobTicketNumber);
        for (int i = 0; i < dataList.size(); i++) {
            DatabasePojo pojo = dataList.get(i);

            SubmitPojo submitPojo = new SubmitPojo();
            submitPojo.setQuestion(pojo.getQuestion());
            submitPojo.setId(pojo.getId());
            submitPojo.setServiceType(pojo.getServiceType());
            submitPojo.setEquipmentType(pojo.getEquipmentType());
            submitPojo.setEquipNumber(pojo.getEquipNumber());
            submitPojo.setJobTicketNumber(pojo.getJobTicketNumber());
            submitPojo.setGroup(pojo.getGroup());
            submitPojo.setQuestionType(pojo.getQuestionType());
            submitPojo.setSignatureLink("" + pojo.getSignatureLink());
            submitPojo.setName(pojo.getName());
            submitPojo.setPreAnswer("" + dataList.get(0).getPreAnswer());
            submitPojo.setUnit("" + pojo.getUnit());
            submitPojo.setDateString("" + GeneralHelpers.currentDate(new Date()));
            submitPojo.setIsPhoto(pojo.getIsPhoto());

            submitPojo.setPartDescription("" + pojo.getPartDescription());
            submitPojo.setPartNumber("" + pojo.getPartNumber());
            submitPojo.setPartQuantity("" + pojo.getPartQuantity());
            submitPojo.setManPower("" + pojo.getManPower());
            submitPojo.setIsRepaired("" + pojo.getIsRepaired());

            if (pojo.getQuestionType().equals("Q")) {
                submitPojo.setAnswer(pojo.getOptionText());
                if (pojo.getIsPhoto().equals("P")) {
                    submitPojo.setPhotoLink(pojo.getPhotoLink());

                } else {
                    submitPojo.setPhotoLink("");
                }
            } else {
                submitPojo.setAnswer(pojo.getAdditionalTextFiled());
                submitPojo.setPhotoLink("");
            }
            submitDB.addSubmitDataToDb(submitPojo);
        }
        submitDB.close();
        localDB.close();


        //delete from  incomplete list after save
        IncompleteDatabase db = new IncompleteDatabase(SurveyActivity.this);
        db.deleteInCompleteEntry(jobTicketNumber);
        db.close();

    }

    private void showSubmitPopup() {

        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);

        alert = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Dialog));
        alert.setTitle("You will not be able to edit later");
        alert.setMessage("Are you sure you would like to submit ?");
        alert.setNegativeButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                GeneralDatabase db = new GeneralDatabase(SurveyActivity.this);
                db.updateGeneralData(jobTicketNumber, "", "", "", "", "", "", "1");
                db.close();
                //    apiCallForService();
                dialog.dismiss();

            }
        });
        alert.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                progressDialog.cancel();

            }
        });
        alert.show();

    }

    // Show a signature pop-up
    private void showSignaturePoup() {
        final Dialog dialog;
        dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.signature_popup);
        dialog.setTitle("CUSTOMER SIGNATURE");

        mSignaturePad = (SignaturePad) dialog.findViewById(R.id.signature_pad);
        mClearButton = (Button) dialog.findViewById(R.id.clear_button);
        mSaveButton = (Button) dialog.findViewById(R.id.save_button);


        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });


        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

                Bitmap photo = ImageUtil.scaleBitmap(signatureBitmap, 150, 150);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 20, stream);
                customerSignature = ImageUtil.convert(photo);

                byte[] imageInByte = stream.toByteArray();
                int length = imageInByte.length / 1024;
                //("SurveyActivity", "Signature size : " + length);

                layoutNextButton.setVisibility(View.GONE);
                layoutMarginLeft.setVisibility(View.GONE);
                layoutSubmitButton.setVisibility(View.VISIBLE);
                layoutPreviousButton.setVisibility(View.GONE);

                //Updating in database
                GeneralDatabase db = new GeneralDatabase(SurveyActivity.this);
                db.updateGeneralData(jobTicketNumber, "", "", "", customerSignature, "", "", "1");

//                showSubmitPopup();

                db.updateGeneralData(jobTicketNumber, "", "", "", "", "", "", "1");
                db.close();
                if (GeneralHelpers.isOnlineWithoutExit(SurveyActivity.this)) {
                    //   apiCallForService();
                }
                dialog.cancel();

            }
        });
        dialog.show();
    }

    private void changeInputTypeForSpecialCase(int position, String question) {
        //        Liquid line sight glass (color) 2
        if (position < 10) {
            if ((question.substring(4)).equals("Liquid line sight glass (color) 1") || (question.substring(4)).equals("Liquid line sight glass (color) 2") || (question.substring(4)).equals("Liquid line sight glass (color) 1") || (question.substring(4)).equals("Liquid line sight glass (color) 2")) {
                mAdditionalAnswerField.setInputType(InputType.TYPE_CLASS_TEXT);

            } else {
                mAdditionalAnswerField.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        } else if (position < 100) {
            if ((question.substring(5)).equals("Liquid line sight glass (color) 1") || (question.substring(5)).equals("Liquid line sight glass (color) 2") || (question.substring(4)).equals("Liquid line sight glass (color) 1") || (question.substring(4)).equals("Liquid line sight glass (color) 2")) {
                mAdditionalAnswerField.setInputType(InputType.TYPE_CLASS_TEXT);

            } else {
                mAdditionalAnswerField.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        } else {
            if ((question.substring(6)).equals("Liquid line sight glass (color) 1") || (question.substring(6)).equals("Liquid line sight glass (color) 2") || (question.substring(4)).equals("Liquid line sight glass (color) 1") || (question.substring(4)).equals("Liquid line sight glass (color) 2")) {
                mAdditionalAnswerField.setInputType(InputType.TYPE_CLASS_TEXT);

            } else {
                mAdditionalAnswerField.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        }
    }

    private void showPopUpDialogForPartDetail() {
        final Dialog dialog;
        dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.popup_dialog_fro_parts_detail);

        dialog.setTitle("Required fields");
        final CheckBox mManPowerCheckBox = (CheckBox) dialog.findViewById(R.id.checkBoxManPower);
        final CheckBox mEquipmentRequiredCheckBox = (CheckBox) dialog.findViewById(R.id.checkBoxEquipmentRequired);
        final EditText manPowerEditText, partDescEditText, partQuantityEditText, partNumberEditText;
        manPowerEditText = (EditText) dialog.findViewById(R.id.editTextManPower);
        partDescEditText = (EditText) dialog.findViewById(R.id.editTextPartDescription);
        partQuantityEditText = (EditText) dialog.findViewById(R.id.editTextPartQuantity);
        partNumberEditText = (EditText) dialog.findViewById(R.id.editTextPartNumber);
        dialog.show();
        mManPowerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    dialog.findViewById(R.id.layoutManPower).setVisibility(View.VISIBLE);
                } else {
                    dialog.findViewById(R.id.layoutManPower).setVisibility(View.GONE);
                }
            }
        });

        mEquipmentRequiredCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    manPowerEditText.setNextFocusDownId(R.id.editTextPartNumber);
                    dialog.findViewById(R.id.layoutEquipmentRequired).setVisibility(View.VISIBLE);
                } else {
                    manPowerEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    dialog.findViewById(R.id.layoutEquipmentRequired).setVisibility(View.GONE);
                }
            }
        });


        //     if (!questionArrayList.get(currentPositionQuestion).getAnswer().equals("")) {

        if (questionArrayList.get(currentPositionQuestion).getPartQuantity() != null && !questionArrayList.get(currentPositionQuestion).getPartDescription().equals("")) {
            partDescEditText.setText("" + questionArrayList.get(currentPositionQuestion).getPartDescription());
        } else {
            partDescEditText.setText("");
        }

        if (questionArrayList.get(currentPositionQuestion).getPartNumber() != null && !questionArrayList.get(currentPositionQuestion).getPartNumber().equals("")) {
            partNumberEditText.setText("" + questionArrayList.get(currentPositionQuestion).getPartNumber());
        } else {
            partNumberEditText.setText("");
        }
        if (questionArrayList.get(currentPositionQuestion).getPartQuantity() != null && !questionArrayList.get(currentPositionQuestion).getPartQuantity().equals("")) {
            partQuantityEditText.setText("" + questionArrayList.get(currentPositionQuestion).getPartQuantity());
        } else {
            partQuantityEditText.setText("");
        }
        if (questionArrayList.get(currentPositionQuestion).getManPower() != null && !questionArrayList.get(currentPositionQuestion).getManPower().equals("")) {
            manPowerEditText.setText("" + questionArrayList.get(currentPositionQuestion).getManPower());
        } else {
            manPowerEditText.setText("");
        }
        //     }

        dialog.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionArrayList.get(currentPositionQuestion).setOptionText("" + optionSelected);

                partNumber = "" + partNumberEditText.getText().toString().trim();
                partQuantity = "" + partQuantityEditText.getText().toString().trim();
                partDescription = "" + partDescEditText.getText().toString().trim();
                manPower = "" + manPowerEditText.getText().toString().trim();

                if (!mEquipmentRequiredCheckBox.isChecked() && !mManPowerCheckBox.isChecked()) {
                    GeneralHelpers.showPopUp(getBaseContext(), "Please select at least one option");
                } else if (mEquipmentRequiredCheckBox.isChecked() && mManPowerCheckBox.isChecked()) {


                    if (TextUtils.isEmpty(manPower)) {
                        manPowerEditText.setError("Required");
                        partQuantityEditText.setError(null);
                        partNumberEditText.setError(null);
                    }
                    if (TextUtils.isEmpty(partNumber)) {
                        partNumberEditText.setError("Required");
                        partQuantityEditText.setError(null);
                        manPowerEditText.setError(null);
                    } else if (TextUtils.isEmpty(partQuantity)) {
                        partNumberEditText.setError(null);
                        manPowerEditText.setError(null);
                        partQuantityEditText.setError("Required");
                    } else {
                        partNumberEditText.setError(null);
                        partQuantityEditText.setError(null);
                        manPowerEditText.setError(null);

                        questionArrayList.get(currentPositionQuestion).setPartNumber("" + partNumber);
                        questionArrayList.get(currentPositionQuestion).setPartQuantity("" + partQuantity);
                        questionArrayList.get(currentPositionQuestion).setPartDescription("" + partDescription);
                        questionArrayList.get(currentPositionQuestion).setManPower("" + manPower);
                        questionArrayList.get(currentPositionQuestion).setCheckedPosition(radioPosition);

                        dialog.cancel();
                    }


                } else {
                    if (mEquipmentRequiredCheckBox.isChecked()) {

                        if (TextUtils.isEmpty(partNumber)) {
                            partNumberEditText.setError("Required");
                            partQuantityEditText.setError(null);
                        } else if (TextUtils.isEmpty(partQuantity)) {
                            partNumberEditText.setError(null);
                            partQuantityEditText.setError("Required");
                        } else {
                            partNumberEditText.setError(null);
                            partQuantityEditText.setError(null);

                            questionArrayList.get(currentPositionQuestion).setPartNumber("" + partNumber);
                            questionArrayList.get(currentPositionQuestion).setPartQuantity("" + partQuantity);
                            questionArrayList.get(currentPositionQuestion).setPartDescription("" + partDescription);
                            questionArrayList.get(currentPositionQuestion).setCheckedPosition(radioPosition);

                            dialog.cancel();
                        }

                    }
                    if (mManPowerCheckBox.isChecked()) {

                        if (TextUtils.isEmpty(manPower)) {
                            manPowerEditText.setError("Required");
                        } else {
                            manPowerEditText.setError(null);
                            questionArrayList.get(currentPositionQuestion).setManPower("" + manPower);
                            questionArrayList.get(currentPositionQuestion).setCheckedPosition(radioPosition);

                            dialog.cancel();
                        }
                    }
                }
            }

        });
    }

    public void onCameraImageClick(View view) {
        takePhoto();
    }

    private void showSubmissionFailedMsg() {
        if (!isErrorred) {
            GeneralHelpers.showPopUp(getBaseContext(), "Submission failed.\nPlease save locally and try again later ");
            isErrorred = true;
        }
    }

    private void submitData() {

        ArrayList<DatabasePojo> techNameList = new ArrayList<DatabasePojo>();
        GeneralDatabase generalDb = new GeneralDatabase(this);
        techNameList = generalDb.getGeneralData(jobTicketNumber);

        if (techNameList.size() > 0) {
            if (techNameList.get(0).getSignatureLink().equals("")) {
                showSignaturePoup();
            } else {
                if (GeneralHelpers.isOnlineWithoutExit(SurveyActivity.this)) {
                    //showSubmitPopup();
                }
            }
        } else {
            showSignaturePoup();
        }
    }

    //call api to get service id according to jobTicketNumber
  /*  private void apiCallForService() {

        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        //api calling to submit basic service detail and we will get a service id along response to send with question api call
        progressDialog.show();

        if (GeneralHelpers.isOnline(this)) {
            CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(SurveyActivity.this, this);

            String urlParameters = null;

            ArrayList<DatabasePojo> techNameList = new ArrayList<DatabasePojo>();
            GeneralDatabase generalDb = new GeneralDatabase(this);
            techNameList = generalDb.getGeneralData(jobTicketNumber);

            generalDb.close();


            try {
                urlParameters = "Pre_Answers=" + dataListSorted.get(0).getPreAnswer() + "&Post_Answers=" + pre_answer + "&survey_start_time=" + techNameList.get(0).getSurveyStartTime() + "&JobNumber=" + jobTicketNumber + "&EquipmentNumber=" + EquipmentNumber
                        + "&ServiceType=" + techNameList.get(0).getServiceType() + "&customer_name=A" + techNameList.get(0).getCustomerName() + "&technician_name=sd" + techNameList.get(0).getTechName() + "&site_name=as" + techNameList.get(0).getSiteName() +
                        serviceList[servicePosition] + "&EquipmentType=" + equipmentTypeList[equipmentTypePosition] + "&SignatureArray=" + techNameList.get(0).getSignatureLink();
                //("Question API Parameters", "" + urlParameters);

            } catch (Exception e) {

                e.printStackTrace();
            }

            serviceInBackground.execute(getString(R.string.API_SERVICE), urlParameters);
        }

    }

    private void apiCallForQuestion(String serviceId) {

        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        LocalDatabase localDB = new LocalDatabase(this);
        dataList = localDB.getAllLocalData(jobTicketNumber);
        localDB.close();

        for (int i = 0; i < dataList.size(); i++) {

            String answer = "";
            if (dataList.get(i).getQuestionType().equals("Q")) {
                answer = "" + dataList.get(i).getOptionText();
            } else {
                answer = "" + dataList.get(i).getAdditionalTextFiled();
            }

            if (GeneralHelpers.isOnline(this)) {
                CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(SurveyActivity.this, this);
                String urlParameters = null;

                try {

                    apiCount = dataList.size();
                    apiCountTemp = 0;
                    String photoLink = "";
                    if (dataList.get(i).getIsPhoto().equals("1")) {
                        LocalDatabase dB = new LocalDatabase(this);
                        photoLink = dB.getImageById(jobTicketNumber, dataList.get(i).getId());
                    }

                    //To solve degree issue at server side
                    String unit = "";
                    if (dataList.get(i).getUnit().trim().equals("°F")) {
                        unit = "0F";

                    } else if (dataList.get(i).getUnit().trim().equals("°C")) {
                        unit = "0C";

                    } else {
                        unit = "" + dataList.get(i).getUnit().toString();
                    }


                    urlParameters = "id=" + dataList.get(i).getId() + "&equipment_required=" + dataList.get(i).getPartQuantity() + "&manpower_required=" + dataList.get(i).getManPower() + "&part_description=" + dataList.get(i).getPartDescription()
                            + "&part_number=" + dataList.get(i).getPartNumber() + "&is_repaired=" + dataList.get(i).getIsRepaired() + "&token_number=" + jobTicketNumber + "&equipment_type=" + equipmentTypeList[equipmentTypePosition] + "&service_type="
                            + serviceList[servicePosition] + "&group=" + dataList.get(i).getGroup() + "&question=" + dataList.get(i).getQuestion() + "&answer=" + answer + "&isPhoto=" + dataList.get(i).getIsPhoto()
                            + "&questionType=" + dataList.get(i).getQuestionType() + "&service_id=" + serviceId + "&unit=" + unit + "&photo=" + photoLink;

                    //("Question URL  ", "" + urlParameters);
                    Log.e("QUESTION ::::::::::::", " : " + urlParameters);
                } catch (Exception e) {

                    e.printStackTrace();

                }
                serviceInBackground.execute(getString(R.string.API_QUESTION), urlParameters);
            }
        }
    }

    private void apiCallForSubmitVerification(String serviceId) {
        if (GeneralHelpers.isOnline(this)) {
            CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(SurveyActivity.this, this);
            String urlParameters = null;

            try {
                urlParameters = "approveid=" + serviceId;
                serviceInBackground.execute(getString(R.string.API_SUBMIT_VERIFY), urlParameters);

            } catch (Exception e) {

                e.printStackTrace();

            }
            //("SurveyActivity", "approveid : " + serviceId);

        }
    }

    @Override
    public void onRemoteCallComplete(String jsonFromWSCall) {
        String response = "";
        String apiCallType = "";
        String serviceId = "";
        //("Response", "" + jsonFromWSCall);
        try {
            JSONObject jsonObject = new JSONObject(jsonFromWSCall);

            response = jsonObject.get("response").toString();
            apiCallType = jsonObject.get("api").toString();
            serviceId = jsonObject.get("id").toString();

            //check response either service or for question
            if (response.equals("success") && apiCallType.equals("service")) {
                if (!serviceId.equals("")) {
                    serviceIdTemp = serviceId;
                    apiCallForQuestion("" + serviceId);
                }
            } else if (response.equals("success") && apiCallType.equals("question")) {
                apiCountTemp = apiCountTemp + 1;
                progressDialog.setMessage("Submitting " + apiCountTemp + " of " + apiCount);
                if (apiCount == apiCountTemp) {
                    apiCallForSubmitVerification(serviceIdTemp);
                }
            } else if (response.equals("success") && apiCallType.equals("approve")) {

                progressDialog.setMessage("Successful");
                GeneralHelpers.showPopUp(getBaseContext(), "Your data submitted successfully.");
                GeneralHelpers.setCurrentFragment(SurveyActivity.this, 2);
                saveSubmitDataToDB();

                setIsSurveyInCompleted(SurveyActivity.this, false);
                if (progressDialog != null) {
                    progressDialog.cancel();
                }
                finish();
            } else {

                if (progressDialog != null) {
                    progressDialog.setMessage("Submission failed.");
                    progressDialog.cancel();
                }
                GeneralHelpers.showPopUp(getBaseContext(), "" + serviceId + "\nYou can save it locally and try again later.");

            }


        } catch (Exception e) {
            //("SurveyActivity", "Error : ");
            progressDialog.setMessage("Submission failed.");
            if (progressDialog != null) {
                showSubmissionFailedMsg();
                progressDialog.cancel();
            }

            e.printStackTrace();
        }

    }

*/
}

