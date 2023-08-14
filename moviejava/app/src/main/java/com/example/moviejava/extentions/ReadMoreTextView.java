package com.example.moviejava.extentions;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class ReadMoreTextView extends AppCompatTextView {
    private int showingLine = 4;
    private String showMore = "read more";
    private String showLess = "hide";
    private final String threeDot = "…";
    private int showMoreTextColor = Color.RED;
    private int showLessTextColor = Color.RED;
    private String mainText = null;
    private boolean isAlreadySet = false;
    private boolean isCollapse = true;

    public ReadMoreTextView(@NonNull Context context) {
        super(context);
        init();
    }
    public ReadMoreTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ReadMoreTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (showingLine >= getLineCount()) return;
                showMoreButton();
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainText = getText().toString();
    }

    private void showMoreButton() {
        String text = getText().toString();
        if (!isAlreadySet) {
            mainText = text;
            isAlreadySet = true;
        }
        StringBuilder showingText = new StringBuilder();
        int start = 0;
        int end;
        for (int i = 0; i < showingLine; i++) {
            end = getLayout().getLineEnd(i);
            showingText.append(text.substring(start, end));
            start = end;
        }
        int specialSpace = 0;
        String newText;
        do {
            newText = showingText.substring(0, showingText.length() - specialSpace);
            newText += threeDot + " " + showMore.toLowerCase();
            setText(newText);
            specialSpace++;
        } while (getLineCount() > showingLine);
        isCollapse = true;
        setShowMoreColoringAndClickable();
    }

    private void setShowMoreColoringAndClickable() {
        SpannableString spannableString = new SpannableString(getText());
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint paint) {
                paint.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View view) {
                setMaxLines(Integer.MAX_VALUE);
                setText(mainText);
                isCollapse = false;
                showLessButton();
            }
        }, getText().length() - showMore.length(), getText().length(), 0);

        spannableString.setSpan(new ForegroundColorSpan(showMoreTextColor),
                getText().length() - showMore.length(),
                getText().length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        setMovementMethod(LinkMovementMethod.getInstance());
        setText(spannableString, BufferType.SPANNABLE);
    }

    private void showLessButton() {
        String text = getText() + " " + showLess.toLowerCase();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint pain) {
                pain.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View view) {
                setMaxLines(showingLine);
                showMoreButton();
            }
        }, text.length() - showLess.length(), text.length(), 0);

        spannableString.setSpan(new ForegroundColorSpan(showLessTextColor),
                text.length() - (threeDot.length() + showLess.length()),
                text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        setMovementMethod(LinkMovementMethod.getInstance());
        setText(spannableString, BufferType.SPANNABLE);
    }

    public void setShowingLine(int lineNumber) {
        if (lineNumber == 0) return;
        showingLine = lineNumber;
        setMaxLines(showingLine);
    }

    public void addShowMoreText(String text) {
        showMore = text;
    }

    public void addShowLessText(String text) {
        showLess = text;
    }

    public void setShowMoreTextColor(int color) {
        showMoreTextColor = color;
    }

    public void setShowLessTextColor(int color) {
        showLessTextColor = color;
    }
}
