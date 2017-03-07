package io.mindjet.jetutil.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewAnimationUtils;

import io.mindjet.jetutil.logger.JLogger;
import io.mindjet.jetutil.version.VersionUtil;

/**
 * Created by Jet on 3/7/17.
 */

public class AnimUtil {

    private static JLogger jLogger = JLogger.get("AnimUtil");
    private final static String REVEAL_WARNING = "Your version is too old to support reveal animation. Please update to Lollipop or later.";

    public static void reveal(final View view, final int duration, final int centerX, final int centerY, final float startRadius, final float endRadius) {
        versionCheck(new Runnable() {
            @Override
            public void run() {
                Animator animator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
                animator.setDuration(duration);
                view.setVisibility(View.VISIBLE);
                animator.start();
            }
        }, REVEAL_WARNING);
    }

    public static void conceal(final View view, final int duration, final int centerX, final int centerY, final float startRadius, final float endRadius) {
        versionCheck(new Runnable() {
            @Override
            public void run() {
                Animator animator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
                animator.setDuration(duration);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
                animator.start();
            }
        }, REVEAL_WARNING);
    }

    public static void concealCenter(View view, int duration) {
        int centerX = view.getWidth() / 2;
        int centerY = view.getHeight() / 2;
        conceal(view, duration, centerX, centerY, getMaxRadius(view), 0);
    }

    public static void revealCenter(View view, int duration) {
        int centerX = view.getWidth() / 2;
        int centerY = view.getHeight() / 2;
        reveal(view, duration, centerX, centerY, 0, getMaxRadius(view));
    }

    public static void concealLeftTop(View view, int duration) {
        conceal(view, duration, 0, 0, getMaxRadius(view) * 2, 0);
    }

    public static void revealLeftTop(View view, int duration) {
        reveal(view, duration, 0, 0, 0, getMaxRadius(view) * 2);
    }

    public static void concealLeftBottom(View view, int duration) {
        conceal(view, duration, 0, view.getHeight(), getMaxRadius(view) * 2, 0);
    }

    public static void revealLeftBottom(View view, int duration) {
        reveal(view, duration, 0, view.getHeight(), 0, getMaxRadius(view) * 2);
    }

    public static void concealRightTop(View view, int duration) {
        conceal(view, duration, view.getWidth(), 0, getMaxRadius(view) * 2, 0);
    }

    public static void revealRightTop(View view, int duration) {
        reveal(view, duration, view.getWidth(), 0, 0, getMaxRadius(view) * 2);
    }

    public static void concealRightBottom(View view, int duration) {
        conceal(view, duration, view.getWidth(), view.getHeight(), getMaxRadius(view) * 2, 0);
    }

    public static void revealRightBottom(View view, int duration) {
        reveal(view, duration, view.getWidth(), view.getHeight(), 0, getMaxRadius(view) * 2);
    }

    private static float getMaxRadius(View view) {
        return Math.max(view.getHeight(), view.getWidth());
    }

    private static void versionCheck(Runnable runnable, String message) {
        if (VersionUtil.afterLollipop()) {
            runnable.run();
        } else {
            jLogger.w(message);
        }
    }

}
