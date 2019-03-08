package com.example.a21601861.cosport.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest implements Callback {
    private static final OkHttpClient client = new OkHttpClient();
    private String URL = getUrl();
    private AtomicReference<String> body;
    private int id;
    private http_request_listenner listenner;

    public void makeRequest(Map<String,String> var, http_request_listenner listenner, int id) {
        this.URL=getUrl();
        this.id=id;
        URL+="?";
        Iterator<String> it=(new HashSet<>(var.keySet())).iterator();
        while(it.hasNext()){
            String key=it.next();
            it.remove();
            URL+=key+"="+var.get(key);
            if(it.hasNext()){
                URL+="&";
            }
        }

        System.out.println(URL);
        this.listenner=listenner;
        Request request = new Request.Builder().url(URL).build();
        client.newCall(request).enqueue(this);
    }

    public void makePostRequest(int mode,HashMap<String,String> PostVar, http_request_listenner listenner, int id){
        this.id=id;
        this.listenner=listenner;
        URL=URL+="?mode="+mode;
        ArrayList<String> key=new ArrayList(PostVar.keySet());

        FormBody.Builder formBuilder = new FormBody.Builder();

// dynamically add more parameter like this:
        for(int i=0;i<key.size();i++){
            formBuilder.add(key.get(i),PostVar.get(key.get(i)));
        }
        RequestBody formBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(this);
        URL=getUrl();

    }

    @Override
    public void onFailure(Call call, IOException e) {
       e.printStackTrace();

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        if (!response.isSuccessful()) {
            throw new IOException(response.toString());
        }
        String txt=response.body().string();
       // this.body.set(response.body().string());

        try {
            this.listenner.notifyResult(txt,this.id);
        }
        catch (Exception e){
            System.out.println("FAILED1");
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return "http://cosport.ddns.net/controller.php";
    }
}
