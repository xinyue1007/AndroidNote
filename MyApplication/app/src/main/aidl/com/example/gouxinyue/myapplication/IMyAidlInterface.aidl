// IMyAidlInterface.aidl
package com.example.gouxinyue.myapplication;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
boolean checkAutoStart(String packageName, String callerApp, String action);
}
