package com.example.shreeram;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shreeram.notification.NotificationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    String[][] chaupaiList;
    private static final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);


        List<Item> items = new ArrayList<Item>();
        items.add(new Item(R.drawable.a));
        items.add(new Item(R.drawable.b));
        items.add(new Item(R.drawable.c));
        items.add(new Item(R.drawable.d));
        items.add(new Item(R.drawable.e));
        items.add(new Item(R.drawable.f));
        items.add(new Item(R.drawable.g));
        items.add(new Item(R.drawable.h));
        items.add(new Item(R.drawable.i));
        items.add(new Item(R.drawable.j));
        items.add(new Item(R.drawable.k));
        items.add(new Item(R.drawable.l));
        items.add(new Item(R.drawable.m));
        items.add(new Item(R.drawable.n));
        items.add(new Item(R.drawable.o));
        items.add(new Item(R.drawable.p));
        items.add(new Item(R.drawable.q));
        items.add(new Item(R.drawable.r));
        items.add(new Item(R.drawable.s));
        items.add(new Item(R.drawable.t));
        items.add(new Item(R.drawable.u));
        items.add(new Item(R.drawable.v));
        items.add(new Item(R.drawable.w));
        items.add(new Item(R.drawable.x));
        items.add(new Item(R.drawable.y));
        items.add(new Item(R.drawable.z));
        items.add(new Item(R.drawable.a1));


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));

        chaupaiList = createListChaupai();

        startRepeatingTask();

    }

    private void startRepeatingTask() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Call your method here
                Random random = new Random();

                int randomNumber = random.nextInt(30);

                NotificationHelper.showNotification(getApplicationContext(), chaupaiList[randomNumber][0], chaupaiList[randomNumber][1]);

//                showNotification(chaupaiList[randomNumber][0], chaupaiList[randomNumber][1]);

                handler.postDelayed(this, 2000000);
            }
        }, 3000);
    }

    private void showNotification(String title, String message) {
        // Create an explicit intent for an activity in your app
        // (This is just an example; replace it with your own intent if needed)
        // Intent intent = new Intent(this, YourTargetActivity.class);
        // PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default_channel_id")
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle(title)
                .setContentText(message)
                // Uncomment the following lines if you want to add an intent
                // .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Get the NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Check if the Android version is Oreo or higher to create a notification channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default_channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        // Notify using the builder
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private String[][] createListChaupai() {
        String[][] chaupaiAnuvadArray = {
                {"सीताराम सीताराम भज प्यारे तू सीताराम।", "ओ प्रिय भक्त, सीता और राम का भजन कर, वह सीताराम हैं।"},
                {"मंगल भवन अमंगल हारी। द्रवहु सुदसरथ अचर बिहारी।", "भगवान राम, जो सुदामा के रूप में दिखाई देते हैं, वह अशुभ को हरने वाले हैं। वे सुमित्रापुत्र और दशरथपुत्र हैं।"},
                {"रघुपति कीन्ही बहुत बड़ाई। तुम मम प्रिय भरतहि सम भाई।", "भगवान राम ने बहुत बड़ी गुणगान की हैं। तुम मेरे प्रिय भरत, तुम्हारा भी सामंजस्य है जैसा कि भाई का होना चाहिए।"},
                {"रघुकुल रीति सदा चली आई। प्राण जाए पर वचन न जाई।", "रघुकुल की रीति हमेशा चली आती है, प्राण जाएं पर उनके वचन कभी नहीं जाते।"},
                {"बिनु पैसे के पूज्य पाठ भगवान। कृपा करहु मैं तुम पर जब जान।", "धन के बिना भगवान का पूजन करना चाहिए। हे रघुकुल नंदन, जब मैं जानूं, तब मेरी पर कृपा करो।"},
                {"मनोजवं मारुततुल्यवेगम्। जितेन्द्रियं बुद्धिमतां वरिष्ठम्।", "भगवान हनुमान, जिनकी गति मारुत के समान है, जो इंद्रियों को जीतने वाले हैं, उनमें बुद्धिमानों के सर्वोत्तम हैं।"},
                {"राम चरित अति पावन सुनी। सिया रहम अभिमान नहीं बढ़ाई।", "रामायण के चरित को सुनने से अत्यंत पवित्र होता है। सीता ने कभी अपने अहंकार को बढ़ावा नहीं दिया।"},
                {"बिनय करहु बिनुता रघुराई। तुम्हरहु नाथ नहीं आन न पाई।", "हे रघुकुल नंदन, बिना विनय के तुम्हारा सामर्थ्य कोई भी नहीं पा सकता।"},
                {"राक्षस भीम जब अवतार लीन्हा। जानकी माई बिबिध भय महीं।", "जब रावण ने भगवान शिव का अवतार लिया, तब सीता माता ने भी विभीषण के साथ भय में विराजमान हुईं।"},
                {"बिराचि हृदय कौ चन्द्रमा। सो आदित्य हृदयम भए हंता।", "भगवान राम के हृदय का समान स्वच्छ और कमल के समान चमकता है, जैसा सूर्य का हृदय होता है।"},
                {"देवी गर्भ समान जो जानी। राक्षसान सुरम सब धनि।", "जिसने देवी सीता को अपने गर्भ में धारण किया, वह धन्य हैं, सर्व राक्षस और देवता उसे पूजते हैं।"},
                {"राम नाम की गाह पे रहा। धरती आकाश अभिमान नहीं भरा।", "भगवान राम का नाम हमेशा मन में बसा रहता है, इससे धरा और आकाश में कभी अहंकार नहीं भरता।"},
                {"भगवान राम ने लंका बसाई। रावण रचाई विभीषण आई।", "रावण की लंका में भगवान राम ने आवास स्थापित किया और उनके साथ विभीषण भी आए।"},
                {"जब रघुकुल पथिक चले बैठे। राम संकट तहाँ भय मिटाए।", "जब रघुकुल के पथिक राम की कथा सुनने बैठे, वहां सभी संकटों का नाश हो गया।"},
                {"सीता जीवन राम की रानी। रघुकुल राजीव नायक ज्यानी।", "सीता माता हैं भगवान राम की पत्नी, जो रघुकुल के राजा को प्रिय थीं।"},
                {"सब पर रहता राम रघुकुल राजा। दुखित कृपाल सब पर भवानी।", "राम, रघुकुल के राजा, सभी पर अपनी कृपा बरसाते हैं, वह सभी दुःखी और कृपाल हैं।"},
                {"संतति न जाने जगत के भवानी। जाके संकट सब पर बलवानी।", "जो संतानें नहीं जानते, वही जगत की भवानी हैं, जो सभी पर अपनी शक्ति से संकट को दूर करती हैं।"},
                {"सुर मुनि अरि सकल तन पुराना। यह जसु राम नाम भगवाना।", "सभी देवता, मुनि, और शत्रु भी, सब जानते हैं कि भगवान का नाम राम है।"},
                {"राम लक्ष्मण सीता रहे सहाई। सुमिरहु राम नाम सब काज सहाई।", "राम, लक्ष्मण, और सीता हमेशा साथ रहें, इसलिए राम का नाम स्मरण करो, वह सभी कार्यों में सहायक होगा।"},
                {"सुनहु रघुनायक पथिक वानी। जीवन सुख धन देय सर्व जनी।", "हे रघुकुल नंदन, तुम्हें सुनना चाहिए, क्योंकि तुम्हारी वाणी से सभी जीवन, सुख, और धन को प्राप्त कर सकते हैं।"},
                {"रामचंद्र भगवान कुशल काला। जनक सुता सकल मंगल विशाला।", "भगवान रामचंद्र, तुम्हारा स्वागत है। जनकनंदिनी सीता, सबका मंगल करती हैं।"},
                {"रघुकुल राजित दुलहिन भूपाला। नाम कीन्हें रखे भगवान सोला।", "रघुकुल राजा भगवान राम की दुलहन हैं। उन्होंने अपने राजा भूपाल का नाम बहुत महत्त्वपूर्ण रखा है।"},
                {"बढ़े सत्य अच्युत अभिमानी। राम नाम जपत भव दुख भागी।", "जो व्यक्ति सत्य, अच्युत, और अभिमानी है, वह राम का नाम जपता है, उसका भवसागर से दुःख हमेशा के लिए दूर हो जाता है।"},
                {"रामसकल गुणग्राम समूहा। अस बर सर्व बड़ाई उन्मोहा।", "सम्पूर्ण गुणों का समूह है भगवान राम, उनमें से कोई भी उनकी उत्कृष्टता को मोहित नहीं कर सकता।"},
                {"राम नाम की गाह पे रहा। धरती आकाश अभिमान नहीं भरा।", "भगवान राम का नाम हमेशा मन में बसा रहता है, इससे धरा और आकाश में कभी अहंकार नहीं भरता।"},
                {"भगवान राम ने लंका बसाई। रावण रचाई विभीषण आई।", "रावण की लंका में भगवान राम ने आवास स्थापित किया और उनके साथ विभीषण भी आए।"},
                {"जब रघुकुल पथिक चले बैठे। राम संकट तहाँ भय मिटाए।", "जब रघुकुल के पथिक राम की कथा सुनने बैठे, वहां सभी संकटों का नाश हो गया।"},
                {"सीता जीवन राम की रानी। रघुकुल राजीव नायक ज्यानी।", "सीता माता हैं भगवान राम की पत्नी, जो रघुकुल के राजा को प्रिय थीं।"},
                {"सब पर रहता राम रघुकुल राजा। दुखित कृपाल सब पर भवानी।", "राम, रघुकुल के राजा, सभी पर अपनी कृपा बरसाते हैं, वह सभी दुःखी और कृपाल हैं।"},
                {"संतति न जाने जगत के भवानी। जाके संकट सब पर बलवानी।", "जो संतानें नहीं जानते, वही जगत की भवानी हैं, जो सभी पर अपनी शक्ति से संकट को दूर करती हैं।"},
                {"सुर मुनि अरि सकल तन पुराना। यह जसु राम नाम भगवाना।", "सभी देवता, मुनि, और शत्रु भी, सब जानते हैं कि भगवान का नाम राम है।"},
                {"राम लक्ष्मण सीता रहे सहाई। सुमिरहु राम नाम सब काज सहाई।", "राम, लक्ष्मण, और सीता हमेशा साथ रहें, इसलिए राम का नाम स्मरण करो, वह सभी कार्यों में सहायक होगा।"},
                {"सुनहु रघुपति नायक भगवाना। सुर मुनि अग्रज जयश्री जाना।", "हे रघुकुल नंदन, भगवान, तुम्हें सुनना चाहिए क्योंकि सभी देवता और मुनि तुम्हारे अग्रज, जयश्री को जानते हैं।"}
        };

        return chaupaiAnuvadArray;
    }
}

