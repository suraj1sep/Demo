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

    String[] SPINNERLIST = {"Document 1", "Document 2", "Document 3", "Document 4", "Document 5", "Document 6"};
    String[] SPINNERLIST1 = {"Document 1", "Document 2", "Document 3", "Document 4", "Document 5", "Document 6"};
    String[] SPINNERLIST2 = {"Document 1", "Document 2", "Document 3", "Document 4", "Document 5", "Document 6"};
    String[] SPINNERLIST3 = {"Document 1", "Document 2", "Document 3", "Document 4", "Document 5", "Document 6"};
    String[] SPINNERLIST4 = {"Document 1", "Document 2", "Document 3", "Document 4", "Document 5", "Document 6"};
    String[] SPINNERLIST5 = {"Document 1", "Document 2", "Document 3", "Document 4", "Document 5", "Document 6"};

    LinearLayout secondcampLL, thirdcampLL, fourth_campLL, fifth_campLL, sixth_campLL;
    ImageView FreeCampAttachmntIV, secondcamp_attachIV, thirdcamp_attachIV, fourth_camp_attachIV, fifth_camp_attachIV, sixth_camp_attachIV;
    ImageView del_img2, del_img3, del_img4, del_img5, del_img6;
    LinearLayout admore_firstLL, del_firstLL;
    Button showcaseSubmitBT;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upload_image);

        FreeCampAttachmntIV = (ImageView) findViewById(R.id.freeattachdoc1);
        secondcamp_attachIV = (ImageView) findViewById(R.id.secondcamp_attachIV);
        thirdcamp_attachIV = (ImageView) findViewById(R.id.thirdcamp_attachIV);
        fourth_camp_attachIV = (ImageView) findViewById(R.id.fourth_camp_attachIV);
        fifth_camp_attachIV = (ImageView) findViewById(R.id.fifth_camp_attachIV);
        sixth_camp_attachIV = (ImageView) findViewById(R.id.sixth_camp_attachIV);

        del_img2 = (ImageView) findViewById(R.id.del_img2);
        del_img3 = (ImageView) findViewById(R.id.del_img3);
        del_img4 = (ImageView) findViewById(R.id.del_img4);
        del_img5 = (ImageView) findViewById(R.id.del_img5);
        del_img6 = (ImageView) findViewById(R.id.del_img6);

        admore_firstLL = (LinearLayout) findViewById(R.id.admore_firstLL);
        del_firstLL = (LinearLayout) findViewById(R.id.del_firstLL);
        showcaseSubmitBT = (Button) findViewById(R.id.showcaseSubmitBT);

        secondcampLL = (LinearLayout) findViewById(R.id.secondcampLL);
        thirdcampLL = (LinearLayout) findViewById(R.id.thirdcampLL);
        fourth_campLL = (LinearLayout) findViewById(R.id.fourth_campLL);
        fifth_campLL = (LinearLayout) findViewById(R.id.fifth_campLL);
        sixth_campLL = (LinearLayout) findViewById(R.id.sixth_campLL);

        // All spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST1);
        MaterialBetterSpinner materialDesignSpinner1 = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner1);
        materialDesignSpinner1.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST2);
        MaterialBetterSpinner materialDesignSpinner2 = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner2);
        materialDesignSpinner2.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST3);
        MaterialBetterSpinner materialDesignSpinner3 = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner3);
        materialDesignSpinner3.setAdapter(arrayAdapter3);

        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST4);
        MaterialBetterSpinner materialDesignSpinner4 = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner4);
        materialDesignSpinner4.setAdapter(arrayAdapter4);

        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST5);
        MaterialBetterSpinner materialDesignSpinner5 = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner5);
        materialDesignSpinner5.setAdapter(arrayAdapter5);

        admore_firstLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Add More", Toast.LENGTH_SHORT).show();

                if (count == 1) {

                    secondcampLL.setVisibility(View.VISIBLE);
                    // del_firstLL.setVisibility(View.VISIBLE);
                    count = 2;
                } else if (count == 2) {
                    thirdcampLL.setVisibility(View.VISIBLE);
                    //  del_firstLL.setVisibility(View.VISIBLE);
                    count = 3;
                } else if (count == 3) {

                    fourth_campLL.setVisibility(View.VISIBLE);
                    //  del_firstLL.setVisibility(View.VISIBLE);
                    count = 4;
                } else if (count == 4) {
                    fifth_campLL.setVisibility(View.VISIBLE);
                    //   del_firstLL.setVisibility(View.VISIBLE);
                    count = 5;
                } else if (count == 5) {
                    admore_firstLL.setVisibility(View.INVISIBLE);
                    sixth_campLL.setVisibility(View.VISIBLE);
                    //   del_firstLL.setVisibility(View.VISIBLE);
                }

            }
        });

        del_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondcampLL.setVisibility(View.GONE);
            }
        });
        del_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thirdcampLL.setVisibility(View.GONE);
            }
        });
        del_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourth_campLL.setVisibility(View.GONE);
            }
        });
        del_img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fifth_campLL.setVisibility(View.GONE);
            }
        });
        del_img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sixth_campLL.setVisibility(View.GONE);
            }
        });

   /*     del_firstLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Deleted Document", Toast.LENGTH_SHORT).show();

                if(countdel==1){

                    secondcampLL.setVisibility(View.GONE);
                    del_firstLL.setVisibility(View.VISIBLE);
                    admore_firstLL.setVisibility(View.VISIBLE);
                    countdel =2;
                }else if (countdel==2){
                    thirdcampLL.setVisibility(View.GONE);
                    del_firstLL.setVisibility(View.VISIBLE);
                    admore_firstLL.setVisibility(View.VISIBLE);
                    countdel = 3;
                }else if (countdel==3){

                    fourth_campLL.setVisibility(View.GONE);
                    del_firstLL.setVisibility(View.VISIBLE);
                    admore_firstLL.setVisibility(View.VISIBLE);
                    countdel = 4;
                }else if (countdel==4){
                    fifth_campLL.setVisibility(View.GONE);
                    del_firstLL.setVisibility(View.VISIBLE);
                    countdel = 5;
                }else if (countdel==5){
                    admore_firstLL.setVisibility(View.VISIBLE);
                    sixth_campLL.setVisibility(View.GONE);
                    del_firstLL.setVisibility(View.VISIBLE);
                }

            }
        });*/

        FreeCampAttachmntIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   i = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

               /* boolean result = Utility.checkPermission(getApplication());
                String res = String.valueOf(result);
                System.out.print("result:: " + res);
                if (result)
                    selectImage();*/
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

        showcaseSubmitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddUploadImageActivity.this, " Test Submit button", Toast.LENGTH_SHORT).show();

               /* if (!RestJsonClient.isNetworkAvailable1(AddUploadImageActivity.this)) {
                    Toast.makeText(getApplication(), "Network Not Available", Toast.LENGTH_SHORT).show();

                }
                else{
                   // new ShowcaseAsynTask().execute();
                }*/
            }
        });

    }

  /*  private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setIcon(R.drawable.upload_photo);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {

                    cameraIntent();


                } else if (options[item].equals("Choose from Gallery")) {

                    galleryIntent();


                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
        startActivityForResult(Intent.createChooser(intent, "Select File"), CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST)
                onSelectFromGalleryResult(data);
            else if (requestCode == CAMERA_REQUEST)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        try {
            if (data != null) {

                thumbnails = (Bitmap) data.getExtras().get("data");
                *//*Uri uri = data.getData();
                imagePath = getPath(uri);*//*
                FreeCampAttachmntIV.setImageBitmap(thumbnails);

                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), thumbnails);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
//                File finalFile = new File(getRealPathFromURI(tempUri));
                imagePath = getPath(tempUri);

                System.out.println(imagePath);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }

        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = this.managedQuery(uri, projection, null, null,
                null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void onSelectFromGalleryResult(Intent data) {

        if (data != null) {
            try {
               *//* Uri uri = data.getData();
                imagePath = getPath(uri);*//*


//                thumbnails = BitmapFactory.decodeFile(imagePath);
                thumbnails = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                FreeCampAttachmntIV.setImageBitmap(thumbnails);

                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = getImageUri(getApplicationContext(), thumbnails);
                //                File finalFile = new File(getRealPathFromURI(tempUri));
                imagePath = getPath(tempUri);

                System.out.println(imagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }*/

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
