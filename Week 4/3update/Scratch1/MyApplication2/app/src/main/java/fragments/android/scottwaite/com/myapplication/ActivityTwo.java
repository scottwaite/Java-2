package fragments.android.scottwaite.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityTwo extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://photos-h.ak.instagram.com/hphotos-ak-xfa1/10735367_343156522522687_148296656_n.jpg");

        String customHtml = "<html><body><div data-role=\"page\" data-control-title=\"Home\" id=\"page1\">\n" +
                "    <div role=\"main\" class=\"ui-content\">\n" +
                "        <div style=\"\" data-controltype=\"image\">\n" +
                "            <img style=\"width: 288px; height: 200px\" src=\"http://photos-a.ak.instagram.com/hphotos-ak-xfa1/10727555_470602719749416_1497996850_n.jpg\">\n" +
                "        </div>\n" +
                "        <div style=\"\" data-controltype=\"image\">\n" +
                "            <img style=\"width: 288px; height: 200px\" src=\"http://photos-h.ak.instagram.com/hphotos-ak-xfa1/10735367_343156522522687_148296656_n.jpg\">\n" +
                "        </div>\n" +
                "        <div style=\"\" data-controltype=\"image\">\n" +
                "            <img style=\"width: 288px; height: 200px\" src=\"http://photos-g.ak.instagram.com/hphotos-ak-xfa1/10724129_743658195715158_355281060_n.jpg\">\n" +
                "        </div>\n" +
                "        <div style=\"\" data-controltype=\"image\">\n" +
                "            <img style=\"width: 288px; height: 200px\" src=\"http://photos-d.ak.instagram.com/hphotos-ak-xfa1/10727555_792630690783891_730766625_n.jpg\">\n" +
                "        </div>\n" +
                "        <div style=\"\" data-controltype=\"image\">\n" +
                "            <img style=\"width: 288px; height: 200px\" src=\"http://photos-h.ak.instagram.com/hphotos-ak-xaf1/10727283_770851142988631_655788802_n.jpg\">\n" +
                "        </div>\n" +
                "        <div style=\"\" data-controltype=\"image\">\n" +
                "            <img style=\"width: 288px; height: 200px\" src=\"http://photos-e.ak.instagram.com/hphotos-ak-xfa1/10727822_697379977045092_655073191_n.jpg\">\n" +
                "        </div>\n" +
                "        <div style=\"\" data-controltype=\"image\">\n" +
                "            <img style=\"width: 288px; height: 200px\" src=\"http://photos-h.ak.instagram.com/hphotos-ak-xaf1/10731661_724072007646575_867228503_n.jpg\">\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <a href=\"http://instagram.com/snoopygrams\" data-transition=\"fade\">\n" +
                "                Profile\n" +
                "            </a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div></body></html>";
        webView.loadData(customHtml, "text/html", "UTF-8");

    }

}