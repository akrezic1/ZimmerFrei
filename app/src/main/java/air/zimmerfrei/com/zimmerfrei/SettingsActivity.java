package air.zimmerfrei.com.zimmerfrei;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.activeandroid.ActiveAndroid;

import java.util.Locale;

/**
 * Created by lovro on 27/01/15.
 */
public class SettingsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Locale locale = Locale.ENGLISH;
    private String languageToLoad;
    public Context context;


    public Spinner getSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spLanguageType);
        return spinner;
    }

    public Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiveAndroid.initialize(this);
        setContentView(R.layout.activity_settings);
        this.setSpinnerData();
        final Spinner spinner = getSpinner();
        final Button saveSettingsButton = (Button) findViewById(R.id.button_save_settings);
        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language(spinner.getSelectedItemPosition());
                changeLang(languageToLoad);
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });
    }


    /**
     * Method that sets data to spinner
     */
    public void setSpinnerData() {
        final Spinner spinner = getSpinner();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.arr_language, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void language(int i) {
        switch (i) {
            case 0:
                languageToLoad = "en";
                break;
            case 1:
                languageToLoad = "hr";
                break;
            case 2:
                languageToLoad = "es";
                break;
        }
    }

    public void changeLang(String lang) {
        Configuration config = getResources().getConfiguration();

        if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {

            SharedPrefsHelper.saveLanguageToSharedPref(lang, this);

            locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration conf = new Configuration(config);
            conf.locale = locale;
            this.getResources().updateConfiguration(conf, this.getResources().getDisplayMetrics());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Method for reloading app's configuration
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (locale != null) {
            Locale.setDefault(locale);
            Configuration config = new Configuration(newConfig);
            config.locale = locale;
            this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
        }
    }

    /**
     * Method that prevents turning app off on back key
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
