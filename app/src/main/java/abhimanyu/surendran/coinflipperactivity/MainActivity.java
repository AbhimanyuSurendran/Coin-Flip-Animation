package abhimanyu.surendran.coinflipperactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView coinImageView;
    private Button flipButton;
    private Random random;
    private boolean isHeads = true; // Indicates the current side of the coin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coinImageView = findViewById(R.id.coinImageView);
        flipButton = findViewById(R.id.flipButton);
        random = new Random();

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCoin();
            }
        });
    }

    private void flipCoin() {
        if (isHeads) {
            coinImageView.setImageResource(R.drawable.front_coin);
            isHeads = false;
        } else {
            coinImageView.setImageResource(R.drawable.back_coin);
            isHeads = true;
        }

        // Load animations
        Animation flipForwardAnimation = AnimationUtils.loadAnimation(this, R.anim.flip_forward);
        Animation flipBackwardAnimation = AnimationUtils.loadAnimation(this, R.anim.flip_backward);

        // Set animation listener to reset the image resource after the animation
        flipForwardAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                coinImageView.setImageResource(isHeads ? R.drawable.front_coin : R.drawable.back_coin);
                coinImageView.startAnimation(flipBackwardAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        // Start the animation
        coinImageView.startAnimation(flipForwardAnimation);
    }
}

