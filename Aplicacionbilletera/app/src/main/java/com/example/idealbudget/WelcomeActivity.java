package com.example.idealbudget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.idealbudget.db.DbHelper;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Crear una instancia del adaptador para el primer GridView
        ImageAdapter adapter1 = new ImageAdapter(this);

        // Obtener referencia del primer GridView
        GridView gridView1 = findViewById(R.id.gridViewCategories);

        // Establecer el adaptador en el primer GridView
        gridView1.setAdapter(adapter1);

        // Adaptador para el segundo GridView
        SingleImageAdapter adapter2 = new SingleImageAdapter(this, new Integer[]{R.drawable.ima1, R.drawable.ima2, R.drawable.ima3, R.drawable.ima4, R.drawable.ima5});

        // Obtener referencia del segundo GridView
        GridView gridView2 = findViewById(R.id.gridViewSingleItem);

        // Establecer el adaptador en el segundo GridView
        gridView2.setAdapter(adapter2);

        gridView2.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) { // Verifica si la posición de la imagen es la de ima3 (posición 2)
                // Abre la actividad MainActivity
                Intent intent1 = new Intent(WelcomeActivity.this, Expenses.class);
                startActivity(intent1);
            }
            if (position == 2) { // Verifica si la posición de la imagen es la de ima3 (posición 2)
                // Abre la actividad MainActivity
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Definimos el onClickListener para el texto "Crear cuenta"
        findViewById(R.id.btnGasto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos un Intent para abrir la actividad de creación de cuenta
                Intent intent = new Intent(WelcomeActivity.this, Gastos.class);

                startActivity(intent);
            }
        });


    }

    // Adaptador para el primer GridView
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Integer[] mImageIds ={
                R.drawable.icon1,
                R.drawable.icon2,
                R.drawable.icon3,
                R.drawable.icon4,
                R.drawable.icon5,
                R.drawable.icon6
        };

        public ImageAdapter(Context context){
            mContext = context;
        }

        public int getCount(){
            return mImageIds.length;
        }

        public Object getItem(int position){
            return null;
        }

        public long getItemId(int position){
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(250, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setAdjustViewBounds(true);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mImageIds[position]);
            return imageView;
        }
    }

    // Adaptador para el segundo GridView
    public class SingleImageAdapter extends BaseAdapter {
        private Context mContext;
        private Integer[] mImageIds;

        public SingleImageAdapter(Context context, Integer[] imageIds) {
            mContext = context;
            mImageIds = imageIds;
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(250, 100));
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setAdjustViewBounds(true);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mImageIds[position]);
            return imageView;
        }
    }
}
