package com.kuwahara.weathearforcastjava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

//    public WifiManager wifiManager;
//    public WifiConfiguration conf;
    private WifiManager Wifi;

    @SuppressLint("WifiManagerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 実機ではwifiには繋がってるので以下の設定はいらないのでは？

//        // WifiManagerのインスタンスを取得する
//        Wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
//
//        //WiFi機能をONにする
//        WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
//        if (wifiManager.isWifiEnabled() == false) {
//            wifiManager.setWifiEnabled(true);
//        }
//
//        // wpaの設定は次を参照した http://blog.dtdweb.com/2013/03/08/android-wifi-network/
//        String ssid = "Buffalo-A-3CF7";
//        WifiConfiguration config = new WifiConfiguration();
//        config.SSID = "\"" + ssid + "\"";
//        config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
//        config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
//        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
//        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
//        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
//        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
//        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
//        config.preSharedKey = "\"nd45usscrxidf\"";
//        int networkId = wifiManager.addNetwork(config); // 失敗した場合は-1となります
//        wifiManager.saveConfiguration();
//        wifiManager.updateNetwork(config);


//        /* wifiの設定 参考:https://teratail.com/questions/2462*/
//        String netSSID = "Buffalo-A-3CF7";
//        String netPass = "nd45usscrxidf";
//
//        WifiConfiguration Wco = new WifiConfiguration();
//
//        // SSIDと暗号化キー(WPA)の設定
//        conf.SSID = "\"" + netSSID + "\"";
//        Wco.preSharedKey = "\""+ netPass +"\"";
//
//        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
//        for( WifiConfiguration i : list ) {
//            if (i.SSID != null && i.SSID.equals("\"" + netSSID + "\"")) {
//                wifiManager.disconnect();
//                wifiManager.enableNetwork(i.networkId, true);
//                wifiManager.reconnect();
//
//                break;
//            }
//        }
            setContentView(R.layout.activity_main);
    }

    public void changeTextView(View view) throws IOException, ParserConfigurationException, SAXException {

        // netを使うことを許可しないとメインスレッドではネットが使えない？
        // 結果、android.os.NetworkOnMainThreadExceptionが発生してるっぽい
        // 参考：https://shirusu-ni-tarazu.hatenablog.jp/entry/2013/01/20/033030

        TextView textView = findViewById(R.id.messageTextView);
        textView.setText("Changed!!");

        String requestURL = "http://weather.livedoor.com/forecast/webservice/json/v1\\?city\\=270000";
        URL url = new URL(requestURL);
        InputStream is = url.openConnection().getInputStream();

        // JSON形式で結果が返るためパースのためにStringに変換する
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while (null != (line = reader.readLine())) {
            sb.append(line);
        }
        String data = sb.toString();
        textView.setText(data);
    }
}
