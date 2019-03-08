package com.example.a21601861.cosport.http;

import com.example.a21601861.cosport.UserPackage.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class DataStorageAccess implements http_request_listenner  {

    public static DataStorageAccess dsa=null;


    private Result result;
    private CountDownLatch cdl=new CountDownLatch(0);
    private HttpRequest rq=new HttpRequest();

    private DataStorageAccess(){
    }

    public void getListUserOfAct(int id){
        this.cdl=new CountDownLatch((int) (this.cdl.getCount()+1));
        Map<String,String> arg=new HashMap<>();
        arg.put("mode","2");
        arg.put("id",""+id);
        this.rq.makeRequest(arg,this,1);
        try {
            this.cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getListActivity(){
        this.cdl=new CountDownLatch((int) (this.cdl.getCount()+1));
        Map<String,String> arg=new HashMap<>();
        arg.put("mode","4");
        this.rq.makeRequest(arg,this,2);
        try {
            this.cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resarchUser(String log){
        this.cdl=new CountDownLatch((int) (this.cdl.getCount()+1));
        Map<String,String> arg=new HashMap<>();
        arg.put("mode","1");
        arg.put("log",""+log);
        this.rq.makeRequest(arg,this,3);
        try {
            this.cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getUser(int id){
        this.cdl=new CountDownLatch((int) (this.cdl.getCount()+1));
        Map<String,String> arg=new HashMap<>();
        arg.put("mode","3");
        arg.put("id",""+id);
        this.rq.makeRequest(arg,this,4);
        try {
            this.cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void getActivity(int id){
        this.cdl=new CountDownLatch((int) (this.cdl.getCount()+1));
        Map<String,String> arg=new HashMap<>();
        arg.put("mode","5");
        arg.put("id",""+id);
        this.rq.makeRequest(arg,this,5);
        try {
            this.cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyResult(String data,int id) {
        if(id==8)
            System.out.println(data);
        this.result=new Result(data);
        this.cdl.countDown();
    }
    public Result getResult(){
        return this.result;
    }
    public static DataStorageAccess getInstance(){
        if(dsa==null){
            dsa=new DataStorageAccess();
        }
        return dsa;
    }

    public void informUserRemove(int userId,int actId) {
        this.cdl=new CountDownLatch((int) (this.cdl.getCount()+1));
        Map<String,String> arg=new HashMap<>();
        arg.put("userId",""+userId);
        arg.put("actId",""+actId);
        arg.put("mode","8");
        this.rq.makeRequest(arg,this,8);
        try {
            this.cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void informUserJoin(int userId, int actId) {
        this.cdl=new CountDownLatch((int) (this.cdl.getCount()+1));
        Map<String,String> arg=new HashMap<>();
        arg.put("userId",""+userId);
        arg.put("actId",""+actId);
        arg.put("mode","7");
        this.rq.makeRequest(arg,this,7);
        try {
            this.cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
