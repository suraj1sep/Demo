package com.tpa.paramount.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.ByteArrayOutputStream;

import extra.Utility;

public class AddUploadImageActivity extends AppCompatActivity {

     LinearLayout secondcampLL, thirdcampLL, fourth_campLL, fifth_campLL, sixth_campLL;
    ImageView FreeCampAttachmntIV, secondcamp_attachIV, thirdcamp_attachIV, fourth_camp_attachIV, fifth_camp_attachIV, sixth_camp_attachIV;
    EditText documentname1, documentname2, documentname3, documentname4, documentname5, documentname6;
    Button uploadBT, nextBT;
    private int count = 1;
    private int countdel = 1;

    static String value = "0";
    int CAP_TURE_REQUEST = 1;
    int GALLERY_REQUEST = 2;
    int GALLERY_REQUEST2 = 3;
    int GALLERY_REQUEST3 = 4;
    int GALLERY_REQUEST4 = 5;
    int GALLERY_REQUEST5 = 6;
    int GALLERY_REQUEST6 = 7;

    int CAMERA_REQUEST = 2;
    String capturedimg;
    String capturedimg2;
    String capturedimg3;
    String capturedimg4;
    String capturedimg5;
    String capturedimg6;
    Bitmap bitmap;
    Bitmap thumbnails;
    String imagePath;
    String  Title1 , Title2, Title3, Title4, Title5, Title6;
    String imageString1, imageString2, imageString3, imageString4, imageString5, imageString6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upload_image);

        documentname1 = (EditText) findViewById(R.id.documentname1);
        documentname2 = (EditText) findViewById(R.id.documentname2);
        documentname3 = (EditText) findViewById(R.id.documentname3);
        documentname4 = (EditText) findViewById(R.id.documentname4);
        documentname5 = (EditText) findViewById(R.id.documentname5);
        documentname6 = (EditText) findViewById(R.id.documentname6);

        FreeCampAttachmntIV = (ImageView) findViewById(R.id.freeattachdoc1);
        secondcamp_attachIV = (ImageView) findViewById(R.id.secondcamp_attachIV);
        thirdcamp_attachIV = (ImageView) findViewById(R.id.thirdcamp_attachIV);
        fourth_camp_attachIV = (ImageView) findViewById(R.id.fourth_camp_attachIV);
        fifth_camp_attachIV = (ImageView) findViewById(R.id.fifth_camp_attachIV);
        sixth_camp_attachIV = (ImageView) findViewById(R.id.sixth_camp_attachIV);

  

        uploadBT = (Button) findViewById(R.id.uploadBT);

        secondcampLL = (LinearLayout) findViewById(R.id.secondcampLL);
        thirdcampLL = (LinearLayout) findViewById(R.id.thirdcampLL);
        fourth_campLL = (LinearLayout) findViewById(R.id.fourth_campLL);
        fifth_campLL = (LinearLayout) findViewById(R.id.fifth_campLL);
        sixth_campLL = (LinearLayout) findViewById(R.id.sixth_campLL);

    
           FreeCampAttachmntIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });

        secondcamp_attachIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST2);
            }
        });

        thirdcamp_attachIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST3);
            }
        });
        fourth_camp_attachIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST4);
            }
        });
        fifth_camp_attachIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST5);
            }
        });
        sixth_camp_attachIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST6);
            }
        });

        uploadBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title1 = documentname1.getText().toString();
                Title2 = documentname2.getText().toString();
                Title3 = documentname3.getText().toString();
                Title4 = documentname4.getText().toString();
                Title5 = documentname5.getText().toString();
                Title6 = documentname6.getText().toString();

                imageString1 = getStringImage(bitmap);
                imageString2 = getStringImage(bitmap);
                imageString3 = getStringImage(bitmap);
                imageString4 = getStringImage(bitmap);
                imageString5 = getStringImage(bitmap);
                imageString6 = getStringImage(bitmap);

                Toast.makeText(AddUploadImageActivity.this, " Test Upload button", Toast.LENGTH_SHORT).show();

                if (!RestJsonClient.isNetworkAvailable1(AddUploadImageActivity.this)) {
                    Toast.makeText(getApplication(), "Network Not Available", Toast.LENGTH_SHORT).show();

                }
                else{
                    new UploadImagesAsynTask().execute();
                }
            }
        });

    }

    class UploadImagesAsynTask extends AsyncTask<Void, Integer, Void> {

        ProgressDialog progressDialog;
        JSONObject result1 = new JSONObject();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getApplication());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Please wait while uploading");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            try {
                if (result1.getString("Response") .equals("Suceess")) {
                    Toast.makeText(getApplication(), result1.getString("Message"),Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplication(),AddUploadImageActivity.class);
                    startActivity(intent);

                } else   Toast.makeText(getApplication(), result1.getString("Message"),Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            publishProgress(1);
            try {

                HttpClient client = new DefaultHttpClient();
                //api url
               // String bannerapi = getApplication().getResources().getString(R.string.base_url) + "index.php/api/addShowcase";
                String bannerapi = "http://192.168.200.124/RESTService/Service1.svc/MultiParser";

                HttpPost post = new HttpPost(bannerapi);

                System.out.println("image path is......." + post);

                MultipartEntity reqEntity = new MultipartEntity();

                //  reqEntity.addPart("user_id_sender", new StringBody(String.valueOf(UrjaPreferences.SSP().getLong(Constants.USER_ID))));

                reqEntity.addPart("s", new StringBody(Title1));
                reqEntity.addPart("s1", new StringBody(Title2));
                reqEntity.addPart("s2", new StringBody(Title3));
                reqEntity.addPart("s3", new StringBody(Title4));
                reqEntity.addPart("s4", new StringBody(Title5));
                reqEntity.addPart("s5", new StringBody(Title6));


                //img
                reqEntity.addPart("stream", new FileBody(new File(capturedimg)));
                reqEntity.addPart("stream1", new FileBody(new File(capturedimg2)));
                reqEntity.addPart("stream2", new FileBody(new File(capturedimg3)));
                reqEntity.addPart("stream3", new FileBody(new File(capturedimg4)));
                reqEntity.addPart("stream4", new FileBody(new File(capturedimg5)));
                reqEntity.addPart("stream5", new FileBody(new File(capturedimg6)));


                post.setEntity(reqEntity);
                HttpResponse response = client.execute(post);

                HttpEntity resEntity = response.getEntity();

                final String response_str = EntityUtils.toString(resEntity);

                result1 = new JSONObject(response_str); //Convert String to JSON Object


            } catch (Exception ex) {
                Log.e("Debug", "error: " + ex.getMessage(), ex);
            }

            return null;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplication().getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            capturedimg = filePath;

            bitmap = BitmapFactory.decodeFile(filePath);
            Drawable d = new BitmapDrawable(bitmap);
            FreeCampAttachmntIV.setImageDrawable(d);
        }
        if (requestCode == GALLERY_REQUEST2 && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplication().getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            capturedimg2 = filePath;

            bitmap = BitmapFactory.decodeFile(filePath);
            Drawable d = new BitmapDrawable(bitmap);
            secondcamp_attachIV.setImageDrawable(d);
        }
        if (requestCode == GALLERY_REQUEST3 && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplication().getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            capturedimg3 = filePath;

            bitmap = BitmapFactory.decodeFile(filePath);
            Drawable d = new BitmapDrawable(bitmap);
           // BitmapDrawable(context.getResources(), canvasBitmap);
            thirdcamp_attachIV.setImageDrawable(d);
        }
        if (requestCode == GALLERY_REQUEST4 && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplication().getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            capturedimg4 = filePath;

            bitmap = BitmapFactory.decodeFile(filePath);
            Drawable d = new BitmapDrawable(bitmap);
            fourth_camp_attachIV.setImageDrawable(d);
        }
        if (requestCode == GALLERY_REQUEST5 && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplication().getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            capturedimg5 = filePath;

            bitmap = BitmapFactory.decodeFile(filePath);
            Drawable d = new BitmapDrawable(bitmap);
            fifth_camp_attachIV.setImageDrawable(d);
        }
        if (requestCode == GALLERY_REQUEST6 && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplication().getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            capturedimg6 = filePath;

            bitmap = BitmapFactory.decodeFile(filePath);
            Drawable d = new BitmapDrawable(bitmap);
            sixth_camp_attachIV.setImageDrawable(d);
        }

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //  bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
