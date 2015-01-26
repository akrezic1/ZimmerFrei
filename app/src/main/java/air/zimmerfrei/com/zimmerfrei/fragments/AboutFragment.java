package air.zimmerfrei.com.zimmerfrei.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import air.zimmerfrei.com.zimmerfrei.R;

/**
 * Fragment with information about this application
 * Created by Andro on 29.10.2014..
 */
public class AboutFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public AboutFragment() {

    }

    public static AboutFragment newInstance(int sectionNumber) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        WebView webView = (WebView) rootView.findViewById(R.id.about_web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://188.226.150.65/mobile/about");
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getActionBar().setTitle(R.string.title_section_about);
    }
}
