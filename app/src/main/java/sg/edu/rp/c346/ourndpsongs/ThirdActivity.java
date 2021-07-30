package sg.edu.rp.c346.ourndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etSinger, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rbThird;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rbThird = findViewById(R.id.rbThird);

        Intent i = getIntent();
        final Song currentSong = (Song) i.getSerializableExtra("currentSong");

        etID.setText(currentSong.getId()+"");
        etTitle.setText(currentSong.getTitle());
        etSinger.setText(currentSong.getSingers());
        etYear.setText(currentSong.getYearReleased()+"");
        rbThird.setRating(currentSong.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentSong.setSingers(etSinger.getText().toString());
                currentSong.setTitle(etTitle.getText().toString());
                currentSong.setYearReleased(Integer.valueOf(etYear.getText().toString()));
                currentSong.setStars(Math.round(rbThird.getRating()));
                dbh.updateSong(currentSong);
                Toast.makeText(ThirdActivity.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                int result = db.deleteSong(currentSong.getId());
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Song deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


}