package examples.exaples.applet;

/**
 * Created by Zornitsa Petkova on 6/3/15.
 */
import java.awt.*;
import java.applet.*;

public class BallApplet extends Applet implements Runnable {
  public static final int ANIMATION_SPEED = 10;
  public static final String IMAGE_NAME_PARAM = "imageName";

  private int mBallX, mBallY, mBallSpeedX, mBallSpeedY;
  private Image mBallImage;
  private Image mImageBuffer;
  private Graphics mImageBufferGraphics;

  private Thread mAnimationThread;
  private boolean mAnimationThreadInterrupted = false;

  /**
   * Applet's init() method. Makes some initializations
   * and loads the ball image. This method is called before
   * creating the animation thread so no synchronization
   * is needed.
   */
  public void init() {
    // Load the ball image from the server
    String imageName = getParameter(IMAGE_NAME_PARAM);
    if (imageName == null) {
      System.err.println("Applet parameter " +
              IMAGE_NAME_PARAM + " is missing.");
      System.exit(-1);
    }
    mBallImage = getImage(getCodeBase(), imageName);

    // Wait for the image to load completely
    MediaTracker tracker = new MediaTracker(this);
    tracker.addImage(mBallImage,0);
    try {
      tracker.waitForAll();  } catch (InterruptedException ie) { }
    if (tracker.statusAll(true) != MediaTracker.COMPLETE) {
      System.err.println("Can not load " + imageName);
      System.exit(-1);
    }

    // Initialize the ball image coordinates and speed
    mBallX = 1;
    mBallY = 1;
    mBallSpeedX = 1;
    mBallSpeedY = 1;

    // Create an image buffer for the animation
    mImageBuffer = createImage(
            getSize().width, getSize().height);
    mImageBufferGraphics = mImageBuffer.getGraphics();
  }

  /**
   * Applet's paint() method. Draws the ball on its current
   * position. This method can be called in the same time
   * from both the applet's thread and from the animation
   * thread so it should be thread safe (synchronized).
   */
  public void paint(Graphics aGraphics) {
    synchronized (this) {
      if (mAnimationThread != null) {
        // Paint in the buffer
        mImageBufferGraphics.fillRect(
                0, 0, getSize().width, getSize().height);
        mImageBufferGraphics.drawImage(
                mBallImage, mBallX, mBallY, this);

        // Copy the buffer contents to the screen
        aGraphics.drawImage(mImageBuffer, 0, 0, this);
      }
    }
  }

  /**
   * Applet's start() method. Creates the animation thread
   * and starts it if it is not already running. This method
   * can be called only from the applet's thread so it does
   * not require synchronization.
   */
  public void start() {
    if (mAnimationThread == null) {
      mAnimationThreadInterrupted = false;
      mAnimationThread = new Thread(this);
      mAnimationThread.start();
    }
  }

  /**
   * Applet's stop() method. Asks the animation thread to
   * stop its execution and waits until it is really stopped.
   * This method is called only from the applet's thread so
   * it does not need synchronization except when accessing
   * the variable mAnimationThreadInterrupted that is common
   * for applet's thread and animation thread.
   */
  public void stop() {
    synchronized (this) {
      mAnimationThreadInterrupted = true;
    }
    try {
      mAnimationThread.join();
    } catch (InterruptedException ie) { }
    mAnimationThread = null;
  }

  /**
   * Animation thread's run() method. Continuously changes
   * the ball position and redraws it and thus an animation
   * effect is achived. This method runs in a separate thread
   * that is especially created for the animation. A
   * synchronization is needed only when accessing variables
   * that are common for the applet's thread and animation
   * thread.
   */
  public void run() {
    // Calculate the animation area size
    int maxX, maxY;
    synchronized (this) {
      maxX = this.getSize().width -
              mBallImage.getWidth(this);
      maxY = this.getSize().height -
              mBallImage.getHeight(this);
    }

    // Perform continuously animation
    while (true) {
      synchronized (this) {
        // Check if the thread should stop
        if (mAnimationThreadInterrupted)
          break;

        // Calculate the new ball coordinates
        if ((mBallX >= maxX) || (mBallX <= 0))
          mBallSpeedX = -mBallSpeedX;
        mBallX = mBallX + mBallSpeedX;
        if ((mBallY >= maxY) || (mBallY <= 0))
          mBallSpeedY = -mBallSpeedY;
        mBallY = mBallY + mBallSpeedY;
      }

      // Redraw the applet contents
      paint(getGraphics());

      // Wait some time to slow down the animation speed
      try {
        Thread.sleep(ANIMATION_SPEED);
      } catch (Exception ex) {}
    }
  }
}
