package com.cears.serviceapp.Fragments;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cears.serviceapp.Activities.MaintenanceActivity;
import com.cears.serviceapp.Activities.PreQuestions;
import com.cears.serviceapp.Activities.SurveyActivity;
import com.cears.serviceapp.AppGlobal.AppConstant;
import com.cears.serviceapp.AppGlobal.AppGlobal;
import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.R;
import com.cears.serviceapp.models.EquipmentTypeSubResponse;
import com.cears.serviceapp.models.GetAllJobSiteSubResponse;
import com.cears.serviceapp.models.GetAllJob_Jobs;
import com.cears.serviceapp.models.LoginResponse;
import com.cears.serviceapp.models.PreQuestionModel;
import com.cears.serviceapp.models.QuestionSubResponse;
import com.cears.serviceapp.models.ServiceTypeSubResponse;
import com.cears.serviceapp.webservice.RestClient;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cears.serviceapp.Fragments.NewJobFragment.ALL_PRE_QUESTIONS;
import static com.cears.serviceapp.Fragments.NewJobFragment.EQUIPMENT_NUMBER_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.JOB_TICKET_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_EQUIPMENT_TYPE;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_SERVICE_TYPE;

/**
 * Created by VIKAS SAHU on 29/12/16.
 */

public class SavedJobFragment extends Fragment {


    private View mainView;
    private ListView mListView;
    TextView textViewDataNotFound;
    ProgressBar progressbar;
    //  private ArrayList<IncompletePojo> dataList;
    private String[] equipmentTypeList, serviceList;
    int equipmentPosition, apiCount = 0, apiCountTemp = 0;
    private ProgressDialog progressDialog;
    String preAnswer = "";
    Boolean isErrorred = false;

    private String optionSelected = "", photo = "", photoString = "", pre_answer = "", jobTicketNumber = "", EquipmentNumber = "", customerSignature = "", serviceIdTemp = "";
    public String serviceType, equipmentType;
    private ArrayList<PreQuestionModel> listPreQuestions;
    String save = "", maintainance = "", fromSaveJob = "";
    int listType = 0;//0-new,1-save,2-submitted
    String jobType;//maintenance,breakdown,inspection etc..
    GeneralDatabase db1;
    CustomAdapter adapter;
    SaveAdapter saveadapter;
    SubmitAdapter submitAdapter;

    List<EquipmentTypeSubResponse> typeModels = new ArrayList<>();
    List<ServiceTypeSubResponse> serviceTypeModels = new ArrayList<>();

    private List<QuestionSubResponse> filteredQuestionList = new ArrayList<QuestionSubResponse>();

    private List<GetAllJob_Jobs> allJobsBySection = new ArrayList<>();
    private List<GetAllJob_Jobs> filteredJobList = new ArrayList<>();
    private List<GetAllJob_Jobs> submitedJobList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = (View) inflater.inflate(R.layout.saved_fragment, container, false);

        db1 = new GeneralDatabase(getActivity());

        mListView = (ListView) mainView.findViewById(R.id.listView);
        textViewDataNotFound = (TextView) mainView.findViewById(R.id.textViewDataNotFound);
        progressbar = (ProgressBar) mainView.findViewById(R.id.progressbar);
        progressDialog = new ProgressDialog(getActivity());
        //  dataList = new ArrayList<IncompletePojo>();
        //   getData();
        //   getAllJobApi();

        typeModels = db1.getEquipmentype();
        serviceTypeModels = db1.getServiceType();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            save = bundle.getString("save");
            jobTicketNumber = bundle.getString(JOB_TICKET_CONSTANT);
            EquipmentNumber = bundle.getString(EQUIPMENT_NUMBER_CONSTANT);
            serviceType = bundle.getString(SELECTED_SERVICE_TYPE);
            equipmentType = bundle.getString(SELECTED_EQUIPMENT_TYPE);
            listType = bundle.getInt("type", 0);
            listPreQuestions = (ArrayList<PreQuestionModel>) bundle.getSerializable(ALL_PRE_QUESTIONS);
            allJobsBySection = (List<GetAllJob_Jobs>) bundle.getSerializable("listMaintenance");
            filteredQuestionList = (List<QuestionSubResponse>) bundle.getSerializable("sectionlist");
            if (allJobsBySection != null && allJobsBySection.size() > 0)
                jobType = allJobsBySection.get(0).getJobType();
        }

        if (save != null && !save.equals("")) {
            addJobTicketApi();
        }

        setadapter();
        return mainView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            setadapter();
        }
    }

    public void setadapter() {

        Bundle b = this.getArguments();
        if (b != null) {
            allJobsBySection = db1.getAllJob(jobType, listType);
            maintainance = b.getString("maintainance");
        }

        filteredJobList.clear();

        for (int i = 0; i < allJobsBySection.size(); i++) {
            if (listType == 0) {
                if (allJobsBySection.get(i).getAll_jobs_state() == null || allJobsBySection.get(i).getAll_jobs_state().length() == 0)
                    filteredJobList.add(allJobsBySection.get(i));
            } else if (listType == 1) {
                if (allJobsBySection.get(i).getAll_jobs_state() != null && allJobsBySection.get(i).getAll_jobs_state().equalsIgnoreCase("save"))
                    filteredJobList.add(allJobsBySection.get(i));
            } else if (listType == 2) {
                if (allJobsBySection.get(i).getAll_jobs_state() != null && allJobsBySection.get(i).getAll_jobs_state().equalsIgnoreCase("submit"))
                    submitedJobList.add(allJobsBySection.get(i));

                Log.e("TAG", "setadapter: " + submitedJobList.size());
            }
        }


        if (submitedJobList != null && submitedJobList.size() > 0) {
            if (submitedJobList.size() > 0) {
                submitAdapter = new SubmitAdapter(getActivity(), submitedJobList);
                mListView.setAdapter(submitAdapter);
                submitAdapter.notifyDataSetChanged();
            } else {
                textViewDataNotFound.setVisibility(View.VISIBLE);
            }
        } else if (filteredJobList != null && filteredJobList.size() > 0 && listType == 0) {
            if (filteredJobList.size() > 0) {
                adapter = new CustomAdapter(getActivity(), filteredJobList);
                mListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                textViewDataNotFound.setVisibility(View.VISIBLE);
            }
        } else {
            if (filteredJobList.size() > 0) {
                saveadapter = new SaveAdapter(getActivity(), filteredJobList);
                mListView.setAdapter(saveadapter);
                saveadapter.notifyDataSetChanged();
            } else {
                textViewDataNotFound.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void addJobTicketApi() {

        if (AppGlobal.isNetwork(getActivity())) {

            ArrayList arrayListObjects = new ArrayList();
            for (int i = 0; i < listPreQuestions.size(); i++) {
                HashMap<String, String> que_ans = new HashMap<>();
                que_ans.put("pre_qid", listPreQuestions.get(i).getPreQid());
                que_ans.put("pre_answer", listPreQuestions.get(i).getAnswer());
                arrayListObjects.add(que_ans);
            }

            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("equipment_type", SELECTED_EQUIPMENT_TYPE);
            data.put("service_type", SELECTED_SERVICE_TYPE);
            data.put("equipment_no", SELECTED_EQUIPMENT_TYPE);
            data.put("site_name", SELECTED_SERVICE_TYPE);
            data.put("customer_name", SELECTED_EQUIPMENT_TYPE);
            data.put("customer_contact_name", SELECTED_SERVICE_TYPE);
            data.put("customer_contact_no", SELECTED_EQUIPMENT_TYPE);
            data.put("work_type", SELECTED_SERVICE_TYPE);
            data.put("start_time", SELECTED_EQUIPMENT_TYPE);
            data.put("end_time", SELECTED_SERVICE_TYPE);
            data.put("lat", SELECTED_EQUIPMENT_TYPE);
            data.put("long", SELECTED_SERVICE_TYPE);
            data.put("remark", SELECTED_EQUIPMENT_TYPE);
            data.put("signature", SELECTED_SERVICE_TYPE);
            data.put("equipment_type", SELECTED_EQUIPMENT_TYPE);
            data.put("service_type", SELECTED_SERVICE_TYPE);
            data.put("pre_answer", arrayListObjects);


            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(getActivity(), AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(getActivity(), AppConstant.PREF_TOKEN));

            HashMap<String, Object> data1 = new HashMap<String, Object>();
            data1.put("data", data);

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "add_job_ticket");
            hashMap.put("request", data1);
            hashMap.put("auth", auth);

            Log.e("SurveyActivity request=", hashMap + "");


            new RestClient(getActivity()).getInstance().get().addjobticket(hashMap).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                Toasty.success(getActivity(), " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            } else {
                                Toasty.error(getActivity(), " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(getActivity(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }


/*
    private void getData() {
        equipmentTypeList = getResources().getStringArray(R.array.equipment_list);
        serviceList = getResources().getStringArray(R.array.service_list);
        ArrayList<IncompletePojo> tempDataList = new ArrayList<IncompletePojo>();
        IncompleteDatabase submitDB = new IncompleteDatabase(getActivity());
        tempDataList = submitDB.getAllInCompletedData();
        submitDB.close();
        Collections.reverse(tempDataList);
        boolean flag = false;
        int length = 0;
        String jobTicketOld = "";
        for (int i = 0; i < tempDataList.size(); i++) {
            IncompletePojo submitPojo = new IncompletePojo();

            if (jobTicketOld.equals("") && flag == false) {
                submitPojo.setJobTicketNumber(tempDataList.get(i).getJobTicketNumber());
                submitPojo.setEquipNumber(tempDataList.get(i).getEquipNumber());
                submitPojo.setServiceType(tempDataList.get(i).getServiceType());
                submitPojo.setEquipmentType(tempDataList.get(i).getEquipmentType());
                submitPojo.setPreAnswer("" + tempDataList.get(i).getPreAnswer());


                if (!isExpired(tempDataList.get(i).getDateString())) {

                    dataList.add(submitPojo);
                }

                jobTicketOld = tempDataList.get(i).getJobTicketNumber();
                flag = true;
            }

            if (!jobTicketOld.equals(tempDataList.get(i).getJobTicketNumber())) {
                jobTicketOld = "";
                length = length + 1;
                flag = false;
            }
            if (length == 30) {
                break;
            }
        }

        if (dataList.size() > 0) {
            mainView.findViewById(R.id.textViewDataNotFound).setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mListView.setAdapter(new SaveAdapter(getActivity()));
        } else {
            mainView.findViewById(R.id.textViewDataNotFound).setVisibility(View.VISIBLE);
        }
    }*/


    String Dat;

    public class CustomAdapter extends BaseAdapter {
        Context mContext;
        private List<GetAllJob_Jobs> alljobarrayList = new ArrayList<>();

        public CustomAdapter(Context mContext, List<GetAllJob_Jobs> alljobarrayList) {
            this.mContext = mContext;
            this.alljobarrayList = alljobarrayList;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getCount() {
            return alljobarrayList.size();
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            SimpleDateFormat simpledateformat = new SimpleDateFormat();
            Calendar calendar = Calendar.getInstance();
            simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
            Dat = simpledateformat.format(calendar.getTime());

            view = inflater.inflate(R.layout.layout_save_custom_adapter, null);
            CardView cardView = (CardView) view.findViewById(R.id.card_view);
            TextView mJobTextView = (TextView) view.findViewById(R.id.textViewTokenNumber);
            TextView mEpmNumberTextView = (TextView) view.findViewById(R.id.textViewEquipmentNumber);
            TextView mEpmTypeTextView = (TextView) view.findViewById(R.id.textViewEquipmentType);
            final TextView mServiceTypeTextView = (TextView) view.findViewById(R.id.textViewServiceName);
            final LinearLayout llServiceName = (LinearLayout) view.findViewById(R.id.llServiceName);

            TextView mDeleteTextView = (TextView) view.findViewById(R.id.textViewDelete);
            TextView mSubmitTextView = (TextView) view.findViewById(R.id.textViewSubmit);

            if (alljobarrayList.size() > 0) {
                progressbar.setVisibility(View.GONE);
            }
            if (maintainance != null && (maintainance.equals("maintainance") || maintainance.equals("m"))) {
                mDeleteTextView.setVisibility(View.GONE);
            }

            if (alljobarrayList.size() > 0) {
                mJobTextView.setText("Job Ticket Number : " + alljobarrayList.get(position).getJobTicketNo());
                mEpmNumberTextView.setText("Equip # : " + alljobarrayList.get(position).getEquipmentId());
                mEpmTypeTextView.setText("Equipment Type Ticket : " + alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                mServiceTypeTextView.setText("Job Type : " + alljobarrayList.get(position).getJobType());
                jobTicketNumber = alljobarrayList.get(position).getJobTicketNo();

                llServiceName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String siteID = allJobsBySection.get(position).getSiteId();
                        GetAllJobSiteSubResponse siteinfo = db1.getAllJob_Site_BySite(siteID);

                        if (maintainance.equals("maintainance")) {
                            if (siteinfo.getEhnsdate() != null && Dat.equals(siteinfo.getEhnsdate() + "")) {
                                Intent i = new Intent(getActivity(), MaintenanceActivity.class);
                                i.putExtra("maintainance", maintainance);
                                i.putExtra("job", allJobsBySection.get(position));
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                startActivityForResult(i, 1);
                            } else {
                                Intent i = new Intent(getActivity(), PreQuestions.class);
                                i.putExtra("maintainance", maintainance);
                                i.putExtra("job", allJobsBySection.get(position));
                                i.putExtra("siteID", siteID);
                                if (filteredQuestionList != null && filteredQuestionList.size() > 0) {
                                    i.putExtra("sectionlist", (Serializable) filteredQuestionList);
                                    Log.e("CustomAdapter", "AdapterQuestionList " + filteredQuestionList.size());
                                }
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                getActivity().startActivity(i);
                            }

                        } else if (maintainance.equals("m")) {
                            if (siteinfo.getEhnsdate() != null && Dat.equals(siteinfo.getEhnsdate() + "")) {
                                Intent i = new Intent(getActivity(), SurveyActivity.class);
                                i.putExtra("job", allJobsBySection.get(position));
                                if (filteredQuestionList != null && filteredQuestionList.size() > 0) {
                                    i.putExtra("sectionlist", (Serializable) filteredQuestionList);
                                    Log.e("CustomAdapter", "AdapterQuestionList " + filteredQuestionList.size());
                                }
                                i.putExtra("Q_type", "Q");
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                if (filteredQuestionList != null && filteredQuestionList.size() > 0) {
                                    getActivity().startActivity(i);
                                }
                            } else {
                                Intent i = new Intent(getActivity(), PreQuestions.class);
                                i.putExtra("maintainance", maintainance);
                                i.putExtra("siteID", siteID);
                                if (filteredQuestionList != null && filteredQuestionList.size() > 0) {
                                    i.putExtra("sectionlist", (Serializable) filteredQuestionList);
                                    Log.e("CustomAdapter", "AdapterQuestionList " + filteredQuestionList.size());
                                }
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                getActivity().startActivity(i);
                            }

                        }
                    }
                });
            }


          /*  if (typeModels.size() <= alljobarrayList.size()) {
                mEpmTypeTextView.setText("Equipment Type Ticket : " + typeModels.get(3).getEquipmentType());
                mServiceTypeTextView.setText("Service Type : " + serviceTypeModels.get(2).getServiceName());
            } else {
                mEpmTypeTextView.setText("Equipment Type Ticket : " + typeModels.get(position).getEquipmentType());
                mServiceTypeTextView.setText("Service Type : " + serviceTypeModels.get(position).getServiceName());
            }*/




   /*         ArrayList<DatabasePojo> techNameList = new ArrayList<DatabasePojo>();

            GeneralDatabase generalDb = new GeneralDatabase(getActivity());
            techNameList = generalDb.getGeneralData(jobTicketNumber);
*/
         /*   if (techNameList.size() > 0) {
                if (!techNameList.get(0).getSignatureLink().equals("")) {

                    mSubmitTextView.setVisibility(View.VISIBLE);
                } else {
                    mSubmitTextView.setVisibility(View.GONE);
                }
            } else {
                mSubmitTextView.setVisibility(View.GONE);
            }

            //To delete data
            mDeleteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert;
                    alert = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Dialog));
                    alert.setTitle("Confirmation");
                    alert.setMessage("Are you sure you want to delete it");
                    alert.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //      deleteOperation(position, mContext);
                            dialog.dismiss();
                        }
                    });
                    alert.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();


                        }
                    });
                    alert.show();


                }
            });


            mSubmitTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert;
                    alert = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Dialog));
                    alert.setTitle("Confirmation");
                    alert.setMessage("Are you sure you want to submit ?");
                    alert.setNegativeButton("SUBMIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
                                jobTicketNumber = alljobarrayList.get(position).getJobTicketNumber();
                                preAnswer = alljobarrayList.get(position).getPreAnswer();

                                //apiCallForService();
                            }
                            dialog.dismiss();
                        }
                    });
                    alert.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();


                        }
                    });
                    alert.show();
                }
            });

            if (techNameList.size() > 0) {
                if (techNameList.get(0).getSignatureLink().equals("")) {
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            GeneralHelpers.setIsSurveyInCompleted(getActivity(), true);
                            //findServiceNumber();
                            Intent intent = new Intent(getActivity(), SurveyActivity.class);
                            intent.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNumber());
                            intent.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipNumber());
                            intent.putExtra(SELECTED_EQUIPMENT_TYPE, equipmentPosition);
                            intent.putExtra(SELECTED_SERVICE_TYPE, servicePosition);
                            intent.putExtra(PRE_ANSWER_CONSTANT, "" + alljobarrayList.get(position).getPreAnswer());
                            startActivityForResult(intent, REQUEST_CODE_SAVED_FRAGMENT);
                        }
                    });
                }
            } else {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GeneralHelpers.setIsSurveyInCompleted(getActivity(), true);
                        //      findServiceNumber();
                        Intent intent = new Intent(getActivity(), SurveyActivity.class);
                        intent.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNumber());
                        intent.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipNumber());
                        intent.putExtra(SELECTED_EQUIPMENT_TYPE, equipmentPosition);
                        intent.putExtra(SELECTED_SERVICE_TYPE, servicePosition);
                        intent.putExtra(PRE_ANSWER_CONSTANT, "" + alljobarrayList.get(position).getPreAnswer());
                        startActivityForResult(intent, REQUEST_CODE_SAVED_FRAGMENT);
                    }
                });
            }
*/

            return view;
        }

        public void appALL(List<GetAllJob_Jobs> data) {
            if (data != null) {
                alljobarrayList.addAll(data);
                notifyDataSetChanged();
            }
        }

    }


    public class SaveAdapter extends BaseAdapter {
        Context mContext;
        private List<GetAllJob_Jobs> alljobarrayList = new ArrayList<>();

        public SaveAdapter(Context mContext, List<GetAllJob_Jobs> alljobarrayList) {
            this.mContext = mContext;
            this.alljobarrayList = alljobarrayList;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getCount() {
            return alljobarrayList.size();
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            SimpleDateFormat simpledateformat = new SimpleDateFormat();
            Calendar calendar = Calendar.getInstance();
            simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
            Dat = simpledateformat.format(calendar.getTime());

            view = inflater.inflate(R.layout.layout_save_custom_adapter, null);
            CardView cardView = (CardView) view.findViewById(R.id.card_view);
            TextView mJobTextView = (TextView) view.findViewById(R.id.textViewTokenNumber);
            TextView mEpmNumberTextView = (TextView) view.findViewById(R.id.textViewEquipmentNumber);
            TextView mEpmTypeTextView = (TextView) view.findViewById(R.id.textViewEquipmentType);
            final TextView mServiceTypeTextView = (TextView) view.findViewById(R.id.textViewServiceName);
            final LinearLayout llServiceName = (LinearLayout) view.findViewById(R.id.llServiceName);

            TextView mDeleteTextView = (TextView) view.findViewById(R.id.textViewDelete);
            TextView mSubmitTextView = (TextView) view.findViewById(R.id.textViewSubmit);

            if (alljobarrayList.size() > 0) {
                progressbar.setVisibility(View.GONE);
            }

            mDeleteTextView.setVisibility(View.VISIBLE);

            /*if (maintainance != null && (maintainance.equals("maintainance") || maintainance.equals("m"))) {
                mDeleteTextView.setVisibility(View.GONE);
            } else {
                mDeleteTextView.setVisibility(View.VISIBLE);
            }*/

            if (alljobarrayList.size() > 0) {
                mJobTextView.setText("Job Ticket Number : " + alljobarrayList.get(position).getJobTicketNo());
                mEpmNumberTextView.setText("Equip # : " + alljobarrayList.get(position).getEquipmentId());
                mEpmTypeTextView.setText("Equipment Type Ticket : " + alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                mServiceTypeTextView.setText("Job Type : " + alljobarrayList.get(position).getJobType());
                jobTicketNumber = alljobarrayList.get(position).getJobTicketNo();

                llServiceName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String siteID = allJobsBySection.get(position).getSiteId();
                        GetAllJobSiteSubResponse siteinfo = db1.getAllJob_Site_BySite(siteID);

                        if (maintainance.equals("maintainance")) {
                            if (siteinfo.getEhnsdate() != null && Dat.equals(siteinfo.getEhnsdate() + "")) {
                                Intent i = new Intent(getActivity(), MaintenanceActivity.class);
                                i.putExtra(maintainance, "maintainance");
                                i.putExtra("job", allJobsBySection.get(position));
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                startActivityForResult(i, 1);
                            } else {
                                Intent i = new Intent(getActivity(), PreQuestions.class);
                                i.putExtra(maintainance, "maintainance");
                                i.putExtra("siteID", siteID);
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                getActivity().startActivity(i);
                            }

                        } else if (maintainance.equals("m")) {
                            if (siteinfo.getEhnsdate() != null && Dat.equals(siteinfo.getEhnsdate() + "")) {
                                Intent i = new Intent(getActivity(), SurveyActivity.class);
                                i.putExtra(maintainance, "maintainance");
                                if (filteredQuestionList != null && filteredQuestionList.size() > 0) {
                                    i.putExtra("sectionlist", (Serializable) filteredQuestionList);
                                    Log.e("SaveAdapter", "AdapterQuestionList " + filteredQuestionList.size());
                                }
                                i.putExtra("Q_type", "Q");
                                i.putExtra("job", allJobsBySection.get(position));
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                if (filteredQuestionList != null && filteredQuestionList.size() > 0) {
                                    getActivity().startActivity(i);
                                }
                            } else {
                                Intent i = new Intent(getActivity(), PreQuestions.class);
                                i.putExtra(maintainance, "maintainance");
                                i.putExtra("siteID", siteID);
                                i.putExtra(JOB_TICKET_CONSTANT, "" + alljobarrayList.get(position).getJobTicketNo());
                                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + alljobarrayList.get(position).getEquipmentId());
                                i.putExtra(SELECTED_EQUIPMENT_TYPE, alljobarrayList.get(position).getEquipmentType().getEquipmentType());
                                i.putExtra(SELECTED_SERVICE_TYPE, alljobarrayList.get(position).getJobType());
                                getActivity().startActivity(i);
                            }

                        }
                    }
                });


                mDeleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });

            }


            return view;
        }

    }


    public class SubmitAdapter extends BaseAdapter {
        Context mContext;
        private List<GetAllJob_Jobs> alljobarrayList = new ArrayList<>();

        public SubmitAdapter(Context mContext, List<GetAllJob_Jobs> alljobarrayList) {
            this.mContext = mContext;
            this.alljobarrayList = alljobarrayList;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getCount() {
            return alljobarrayList.size();
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_save_custom_adapter, null);
            CardView cardView = (CardView) view.findViewById(R.id.card_view);
            TextView mJobTextView = (TextView) view.findViewById(R.id.textViewTokenNumber);
            TextView mEpmNumberTextView = (TextView) view.findViewById(R.id.textViewEquipmentNumber);
            TextView mEpmTypeTextView = (TextView) view.findViewById(R.id.textViewEquipmentType);
            final TextView mServiceTypeTextView = (TextView) view.findViewById(R.id.textViewServiceName);
            final LinearLayout llServiceName = (LinearLayout) view.findViewById(R.id.llServiceName);

            TextView mDeleteTextView = (TextView) view.findViewById(R.id.textViewDelete);
            TextView mSubmitTextView = (TextView) view.findViewById(R.id.textViewSubmit);

            mDeleteTextView.setVisibility(View.GONE);

            if (alljobarrayList.size() > 0) {
                progressbar.setVisibility(View.GONE);
            }


            return view;
        }

        public void appALL(List<GetAllJob_Jobs> data) {
            if (data != null) {
                alljobarrayList.addAll(data);
                notifyDataSetChanged();
            }
        }

    }


/*    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SAVED_FRAGMENT) {
        }
    }

    private void findServiceNumber() {
        servicePosition = 1;
        equipmentPosition = 1;
        for (int i = 0; i < serviceList.length; i++) {
            if (dataList.get(0).getServiceType().equals(serviceList[i])) {
                servicePosition = i;

            }
        }

        for (int i = 0; i < equipmentTypeList.length; i++) {
            if (dataList.get(0).getEquipmentType().equals(equipmentTypeList[i])) {
                equipmentPosition = i;
            }
        }

    }

    // save data after submission mode
    private void saveSubmitDataToDB() {
        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        LocalDatabase localDB = new LocalDatabase(getActivity());
        SubmitDatabase submitDB = new SubmitDatabase(getActivity());

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
        IncompleteDatabase db = new IncompleteDatabase(getActivity());
        db.deleteInCompleteEntry(jobTicketNumber);
        db.close();

    }

    //comparing 30 days old
    private boolean isExpired(String date) {

        Boolean result = false;

        try {
            Date today = new Date();
            Calendar cal = new GregorianCalendar();
            cal.setTime(today);
            cal.add(Calendar.DAY_OF_MONTH, -30);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date savedDate = format.parse(date);

            if (cal.getTimeInMillis() > savedDate.getTime()) {
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void deleteOperation(int position, Context mContext) {
        IncompleteDatabase db = new IncompleteDatabase(mContext);
        db.deleteInCompleteEntry(dataList.get(position).getJobTicketNumber());
        db.close();
        GeneralHelpers.setCurrentFragment(getActivity(), 1);
        ((MainActivity) getActivity()).setView();

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
                GeneralHelpers.showPopUp(getActivity(), "Your data submitted successfully.");
                GeneralHelpers.setCurrentFragment(getActivity(), 2);
                saveSubmitDataToDB();

                if (progressDialog != null) {
                    progressDialog.cancel();
                }
                ((MainActivity) getActivity()).setView();
//                getActivity().finish();
            } else {

                if (progressDialog != null) {
                    showSubmissionFailedMsg();
                    progressDialog.setMessage("Submission failed.");
                    GeneralHelpers.setCurrentFragment(getActivity(), 0);
                    progressDialog.cancel();
                }

            }


        } catch (Exception e) {
            //("SurveyActivity", "Error : ");

            if (progressDialog != null) {
                GeneralHelpers.setCurrentFragment(getActivity(), 0);
                progressDialog.setMessage("Submission failed.");
                showSubmissionFailedMsg();
                progressDialog.cancel();
            }
            e.printStackTrace();
        }

    }

    private void showSubmissionFailedMsg() {
        if (!isErrorred) {
            GeneralHelpers.showPopUp(getActivity(), "Sorry submission failed.\nPlease try again later ");
            isErrorred = true;
        }
    }

    private void apiCallForService() {

        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        //api calling to submit basic service detail and we will get a service id along response to send with question api call
        progressDialog.show();

        if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
            CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(getActivity(), this);

            String urlParameters = null;

            ArrayList<DatabasePojo> techNameList = new ArrayList<DatabasePojo>();
            GeneralDatabase generalDb = new GeneralDatabase(getActivity());
            techNameList = generalDb.getGeneralData(jobTicketNumber);

            try {
                urlParameters = "Pre_Answers=" + dataList.get(0).getPreAnswer() + "&Post_Answers=" + dataList + "&survey_start_time=" + techNameList.get(0).getSurveyStartTime() + "&JobNumber=" + jobTicketNumber + "&EquipmentNumber=" + techNameList.get(0).getEquipNumber()
                        + "&ServiceType=" + techNameList.get(0).getServiceType() + "&customer_name=A" + techNameList.get(0).getCustomerName() + "&technician_name=sd" + techNameList.get(0).getTechName() + "&site_name=as" + techNameList.get(0).getSiteName() +
                        serviceList[servicePosition] + "&EquipmentType=" + equipmentTypeList[equipmentPosition] + "&SignatureArray=" + techNameList.get(0).getSignatureLink();
                //("Question API Parameters", "" + urlParameters);

            } catch (Exception e) {

                e.printStackTrace();
            }

            serviceInBackground.execute(getString(R.string.API_SERVICE), urlParameters);
        }

    }

    private void apiCallForQuestion(String serviceId) {

        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        LocalDatabase localDB = new LocalDatabase(getActivity());
        dataList = localDB.getAllLocalData(jobTicketNumber);
        localDB.close();

        for (int i = 0; i < dataList.size(); i++) {

            String answer = "";
            if (dataList.get(i).getQuestionType().equals("Q")) {
                answer = "" + dataList.get(i).getOptionText();
            } else {
                answer = "" + dataList.get(i).getAdditionalTextFiled();
            }

            if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
                CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(getActivity(), this);
                String urlParameters = null;

                try {


                    //To solve degree issue at server side
                    String unit = "";
                    if (dataList.get(i).getUnit().trim().equals("°F")) {
                        unit = "0F";

                    } else if (dataList.get(i).getUnit().trim().equals("°C")) {
                        unit = "0C";

                    } else {
                        unit = "" + dataList.get(i).getUnit().toString();
                    }

                    apiCount = dataList.size();
                    apiCountTemp = 0;
                    String photoLink = "";
                    if (dataList.get(i).getIsPhoto().equals("1")) {
                        LocalDatabase dB = new LocalDatabase(getActivity());
                        photoLink = dB.getImageById(jobTicketNumber, dataList.get(i).getId());
                    }
                    urlParameters = "id=" + dataList.get(i).getId() + "&equipment_required=" + dataList.get(i).getPartQuantity() + "&manpower_required=" + dataList.get(i).getManPower() + "&part_description=" + dataList.get(i).getPartDescription()
                            + "&part_number=" + dataList.get(i).getPartNumber() + "&is_repaired=" + dataList.get(i).getIsRepaired() + "&token_number=" + jobTicketNumber + "&equipment_type=" + dataList.get(0).getEquipmentType() + "&service_type="
                            + serviceList[servicePosition] + "&group=" + dataList.get(i).getGroup() + "&question=" + dataList.get(i).getQuestion() + "&answer=" + answer + "&isPhoto=" + dataList.get(i).getIsPhoto()
                            + "&questionType=" + dataList.get(i).getQuestionType() + "&service_id=" + serviceId + "&unit=" + unit + "&photo=" + photoLink;

                    //("Question URL  ", "" + urlParameters);
                } catch (Exception e) {

                    e.printStackTrace();

                }
                serviceInBackground.execute(getString(R.string.API_QUESTION), urlParameters);
            }
        }
    }

    private void apiCallForSubmitVerification(String serviceId) {
        if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
            CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(getActivity(), this);
            String urlParameters = null;

            try {
                urlParameters = "approveid=" + serviceId;
                serviceInBackground.execute(getString(R.string.API_SUBMIT_VERIFY), urlParameters);

            } catch (Exception e) {

                e.printStackTrace();

            }
            //("SurveyActivity", "approveid : " + serviceId);

        }
    }*/


}