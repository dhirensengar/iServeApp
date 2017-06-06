package com.cears.serviceapp.webservice;

import android.content.Context;
import android.support.annotation.NonNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by cs-6 on 4/29/2017.
 */

public class ApiManager {

    Context context;

    public ApiManager(Context context) {
        this.context = context;
    }



/*
    public void getAllCalendars() {
        if (AppGlobal.isNetwork(context)) {


            HashMap<String, String> params = new HashMap<>();

            params.put("userid", AppGlobal.getStringPreference(context, AppConstant.PREF_USERID));


            new RestClient(context).getInstance().get().GetCal(params).enqueue(new Callback<GetCalResponse>() {
                @Override
                public void onResponse(Call<GetCalResponse> call, Response<GetCalResponse> response) {

                    Log.e("Tag getCalenders", "Response:" + response.body());
                    Log.e("Tag getCalenders", "Response:" + response.isSuccessful());
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equalsIgnoreCase("1")) {
                            Log.e("Tag getCalenders", "Response:" + response.body().getSuccess());

                            DatabaseHelper helper = new DatabaseHelper(context);


                            ArrayList<CalenderModel> calenderModelList = new ArrayList<CalenderModel>();
                            calenderModelList = response.body().getData();
                            long a = -1;
                            if (calenderModelList.size() > 0) {
                                for (int i = 0; i < calenderModelList.size(); i++) {
                                    CalendarModelF calendarModelFs = new CalendarModelF();
                                    try {
                                        calendarModelFs.setCalUniqueId(calenderModelList.get(i).getCalid());
                                        calendarModelFs.setCalendername(calenderModelList.get(i).getTitle());
                                        calendarModelFs.setColor(calenderModelList.get(i).getColor());
                                        calendarModelFs.setUserid(calenderModelList.get(i).getUserid());
                                        calendarModelFs.setCalFlag("1");
                                        calendarModelFs.setCalStatus(calenderModelList.get(i).getIsdelete());
                                        a = helper.UpdateCalenderFromSync(calendarModelFs);

                                        // get all calendars event
                                        //getAllEvents(calenderModelList.get(i).getCalid());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                if (a > 0) {
                                    Intent broadcastIntentDashBoard = new Intent();
                                    if (HomeActivity.homeActivity != null) {
                                        broadcastIntentDashBoard.setAction(HomeFragment.MyWebRequestReceiver.PROCESS_RESPONSE);
                                    }
                                    broadcastIntentDashBoard.addCategory(Intent.CATEGORY_DEFAULT);
                                    broadcastIntentDashBoard.putExtra("inserted_from_network", "true");
                                    context.sendBroadcast(broadcastIntentDashBoard);
                                }
                            }

                           */
/* Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);*//*

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetCalResponse> call, Throwable t) {
                    Log.e("Tag getCalenders", "Response:" + "No");
                    t.printStackTrace();

                }
            });


        } else {
            AppGlobal.displayAlertDilog(context, "Network Problem", "Network not Available");
        }
    }

    public void addEvent(final EventCalenderModel model) {
        if (AppGlobal.isNetwork(context)) {

            HashMap<String, String> params = new HashMap<>();
            params.put("userid", model.getUserid());
            params.put("calid", model.getCalUniqueId());
            params.put("uniqueid", model.getMultiId());
            params.put("fid", "");
            params.put("title", model.getTitle());
            params.put("notes", model.getDescription());
            params.put("event", model.getEventId() + "");
            params.put("localtion", model.getLocaltion());
            params.put("dates", model.getSelectedDate());
            params.put("starttime", model.getStarttime());
            params.put("endtime", model.getEndtime());
            params.put("reminder", model.getReminder());
            params.put("repeat", model.getRepeat());
            params.put("thumb", model.getThumb());
            params.put("image", "");
            params.put("doc", "");
            params.put("isdelete", model.getEventStatus());
            params.put("edit_rule", model.getRuleEdit());
            params.put("delete_rule", model.getRuleDelete());


            new RestClient(context).getInstance().get().AddEvent(params).enqueue(new Callback<AddEventApiResponce>() {
                @Override
                public void onResponse(Call<AddEventApiResponce> call, Response<AddEventApiResponce> response) {


                    Log.e("Tag AddEvent", "Response:" + response.isSuccessful());
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equalsIgnoreCase("1")) {
                            Log.e("Tag AddEvent", "Response:" + response.body());

                            DatabaseHelper helper = new DatabaseHelper(context);
                            try {
                                helper.EditCalFlag(model.getCalid(), response.body().getData().getId(), model.getEventStatus()
                                        , model.getCalid(), AppGlobal.getStringPreference(context, WsConstant.PREF_USERID));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                           */
/* Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);*//*

                        }
                    }
                }

                @Override
                public void onFailure(Call<AddEventApiResponce> call, Throwable t) {
                    Log.e("Tag AddEvent", "Response:" + "No");

                }
            });


        } else {
            AppGlobal.displayAlertDilog(context, "Network Problem", "Network not Available");
        }

    }

    public void getAllEvents(String calendar_id) {
        if (AppGlobal.isNetwork(context)) {


            HashMap<String, String> params = new HashMap<>();

            params.put("userid", AppGlobal.getStringPreference(context, WsConstant.PREF_USERID));
            params.put("calid", calendar_id);


            new RestClient(context).getInstance().get().getEvents(params).enqueue(new Callback<GetCalResponse>() {
                @Override
                public void onResponse(Call<GetCalResponse> call, Response<GetCalResponse> response) {

                    Log.e("Tag getEvents", "Response:" + response.body());
                    Log.e("Tag getEvents", "Response:" + response.isSuccessful());
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equalsIgnoreCase("1")) {
                            Log.e("Tag getEvents", "Response:" + response.body().getSuccess());

                            DatabaseHelper helper = new DatabaseHelper(context);


                            ArrayList<CalenderModel> calenderModelList = new ArrayList<CalenderModel>();
                            calenderModelList = response.body().getData();
                            long a = -1;
                            if (calenderModelList.size() > 0) {
                                for (int i = 0; i < calenderModelList.size(); i++) {
                                    CalendarModelF calendarModelFs = new CalendarModelF();
                                    try {
                                        calendarModelFs.setCalUniqueId(calenderModelList.get(i).getCalid());
                                        calendarModelFs.setCalendername(calenderModelList.get(i).getTitle());
                                        calendarModelFs.setColor(calenderModelList.get(i).getColor());
                                        calendarModelFs.setUserid(calenderModelList.get(i).getUserid());
                                        calendarModelFs.setCalFlag("1");
                                        calendarModelFs.setCalStatus(calenderModelList.get(i).getIsdelete());
                                        a = helper.UpdateCalenderFromSync(calendarModelFs);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                if (a > 0) {
                                    Intent broadcastIntentDashBoard = new Intent();
                                    if (HomeActivity.homeActivity != null) {
                                        broadcastIntentDashBoard.setAction(HomeFragment.MyWebRequestReceiver.PROCESS_RESPONSE);
                                    }
                                    broadcastIntentDashBoard.addCategory(Intent.CATEGORY_DEFAULT);
                                    broadcastIntentDashBoard.putExtra("inserted_from_network", "true");
                                    context.sendBroadcast(broadcastIntentDashBoard);
                                }
                            }

                           */
/* Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);*//*

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetCalResponse> call, Throwable t) {
                    Log.e("Tag getCalenders", "Response:" + "No");
                    t.printStackTrace();

                }
            });


        } else {
            AppGlobal.displayAlertDilog(context, "Network Problem", "Network not Available");
        }
    }

    public void callGetCalendarEventAPi() {
        if (AppGlobal.isNetwork(context)) {


            HashMap<String, String> params = new HashMap<>();

            params.put("userid", AppGlobal.getStringPreference(context, WsConstant.PREF_USERID));


            new RestClient(context).getInstance().get().GetCal(params).enqueue(new Callback<GetCalResponse>() {
                @Override
                public void onResponse(Call<GetCalResponse> call, Response<GetCalResponse> response) {


                    Log.e("Tag", "Response:" + response.isSuccessful());
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equalsIgnoreCase("1")) {
                            Log.e("Tag", "Response:" + response.body().getSuccess());

                            DatabaseHelper helper = new DatabaseHelper(context);


                            ArrayList<CalenderModel> calenderModelList = new ArrayList<CalenderModel>();
                            calenderModelList = response.body().getData();
                            long a = -1;
                            if (calenderModelList.size() > 0) {
                                for (int i = 0; i < calenderModelList.size(); i++) {
                                    CalendarModelF calendarModelFs = new CalendarModelF();
                                    try {
                                        calendarModelFs.setCalUniqueId(calenderModelList.get(i).getCalid());
                                        calendarModelFs.setCalendername(calenderModelList.get(i).getTitle());
                                        calendarModelFs.setColor(calenderModelList.get(i).getColor());
                                        calendarModelFs.setUserid(calenderModelList.get(i).getUserid());
                                        calendarModelFs.setCalFlag("1");
                                        calendarModelFs.setCalStatus(calenderModelList.get(i).getIsdelete());
                                        a = helper.UpdateCalenderFromSync(calendarModelFs);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                if (a > 0) {
                                    Intent broadcastIntentDashBoard = new Intent();
                                    if (HomeActivity.homeActivity != null) {
                                        broadcastIntentDashBoard.setAction(HomeFragment.MyWebRequestReceiver.PROCESS_RESPONSE);
                                    }
                                    broadcastIntentDashBoard.addCategory(Intent.CATEGORY_DEFAULT);
                                    broadcastIntentDashBoard.putExtra("inserted_from_network", "true");
                                    context.sendBroadcast(broadcastIntentDashBoard);
                                }
                            }

                           */
/* Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);*//*

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetCalResponse> call, Throwable t) {
                    Log.e("Tag", "Response:" + "No");

                }
            });


        } else {
            AppGlobal.displayAlertDilog(context, "Network Problem", "Network not Available");
        }
    }


    public void editUserProfile(LoginData model) {
        if (AppGlobal.isNetwork(context)) {

            String imagefile = model.getProfilepic();

            File imagefile2 = new File(imagefile);


            if (imagefile == null) {
                HashMap<String, String> map = new HashMap<>();
                map.put("userid", model.getUserid());
                map.put("fullname", model.getFullname());
                map.put("birthdate", model.getBirthdate());


                new RestClient(context).getInstance().get().EditProfile2(map).enqueue(new Callback<EditProfileResponse>() {
                    @Override
                    public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {

                        Log.e("Tag", "Response:" + response.isSuccessful());
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess().equalsIgnoreCase("1")) {

                                AppGlobal.setStringPreference(context, response.body().getData().getFullname(), WsConstant.PREF_USERNAME);
                                AppGlobal.setStringPreference(context, response.body().getData().getEmail(), WsConstant.PREF_USERMAIL);
                                HomeActivity.username.setText(AppGlobal.getStringPreference(context, WsConstant.PREF_USERNAME));
                                HomeActivity.useremail.setText(AppGlobal.getStringPreference(context, WsConstant.PREF_USERMAIL));

                                DatabaseHelper helper = new DatabaseHelper(context);
                                helper.updateuserFlag(response.body().getData().getUserid());
                                helper.close();

                                // getActivity().onBackPressed();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                        Log.e("Tag", "Response:" + "No");

                    }
                });

            } else {

                RequestBody userid = createPartFromString(model.getUserid());
                RequestBody fullname = createPartFromString(model.getFullname());
                RequestBody birthdate = createPartFromString(model.getBirthdate());

                HashMap<String, RequestBody> map = new HashMap<>();
                map.put("userid", userid);
                map.put("fullname", fullname);
                map.put("birthdate", birthdate);


                if (imagefile2.exists()) {

                    RequestBody requestBody = RequestBody.create(MediaType.parse("**
/*"), imagefile);
                    MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("profilepic", imagefile2.getName(), requestBody);


                    new RestClient(context).getInstance().get().EditProfile(map, fileToUpload).enqueue(new Callback<EditProfileResponse>() {
                        @Override
                        public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {

                            Log.e("Tag", "Response:" + response.isSuccessful());
                            if (response.isSuccessful()) {
                                if (response.body().getSuccess().equalsIgnoreCase("1")) {
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                            Log.e("Tag", "Response:" + "No");

                        }
                    });
                }

            }

        } else {
            //   AppGlobal.displayAlertDilog(getContext(), "Network Problem", "Network not Available");
        }

    }


    public void saveCalendar(final CalendarModelF model) {
        if (AppGlobal.isNetwork(context)) {


            HashMap<String, String> params = new HashMap<>();
            params.put("userid", model.getUserid());
            params.put("calid", model.getCalUniqueId());
            params.put("title", model.getCalendername());
            params.put("color", model.getColor());
            params.put("isdelete", model.getCalStatus());


            new RestClient(context).getInstance().get().AddCal(params).enqueue(new Callback<AddCalendarResponse>() {
                @Override
                public void onResponse(Call<AddCalendarResponse> call, Response<AddCalendarResponse> response) {

                    Log.e("Tag", "Response:" + response.isSuccessful());
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equalsIgnoreCase("1")) {
                            Log.e("Tag", "Response:" + response.body().getSuccess());

                            DatabaseHelper helper = new DatabaseHelper(context);
                            try {
                                helper.EditCalFlag(model.getCalid(), response.body().getData().getId(), model.getCalStatus()
                                        , model.getCalid(), AppGlobal.getStringPreference(context, WsConstant.PREF_USERID));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<AddCalendarResponse> call, Throwable t) {
                    Log.e("Tag", "Response:" + "No");

                }
            });


        } else {
            AppGlobal.displayAlertDilog(context, "Network Problem", "Network not Available");
        }

    }

*/


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

}
