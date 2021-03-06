package com.coatedmoose.daggerizedtest;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.coatedmoose.daggerizedandroid.ActivityModule;
import com.coatedmoose.daggerizedandroid.DaggerFragmentActivity;
import com.coatedmoose.daggerizedandroid.qualifier.ForActivity;
import com.coatedmoose.daggerizedandroid.qualifier.ForApplication;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;


public class MainActivity extends DaggerFragmentActivity implements View.OnClickListener, TextWatcher {

    @Inject
    @ForApplication
    Context applicationContext;

    @Inject
    @ForActivity
    Context activityContext;

    @Inject
    @Named("saved_text")
    StringPreference savedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.app_ctx_test).setOnClickListener(this);
        findViewById(R.id.activity_ctx_test).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText editableText = (EditText) findViewById(R.id.saveable_text);
        editableText.setText(savedText.get());
        editableText.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.app_ctx_test:
                Toast.makeText(applicationContext, R.string.toast_test_passed, Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_ctx_test:
                Toast.makeText(activityContext, R.string.toast_test_passed, Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Cannot handle a click there");
        }
    }

    @Override
    public List<Object> getModules() {
        List<Object> modules = super.getModules();
        modules.add(new MainActivityModule());
        return modules;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        savedText.set(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Do nothing
    }

    @Module(
            addsTo = MyApplicationModule.class,
            includes = ActivityModule.class,
            injects = MainActivity.class
    )
    class MainActivityModule {
    }
}
