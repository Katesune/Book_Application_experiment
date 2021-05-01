package com.example.bookapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class StringResourceHelper {
    Context c;
    int q_number;

    StringResourceHelper(Context context) {
        c = context;
    }

    //ставим номер вопроса
    public void setQuestionNumber(int num) {
        q_number = num;
    }

    public int getIdResToTYpe(String name, String type) {
        return c.getResources().getIdentifier(name, type, c.getPackageName());
    }

    //ищет строковый ресурс по уникальному идентификатору
    public int getIdRes(String name) {
        return c.getResources().getIdentifier(name, "string", c.getPackageName());
    }

    public int getIdDrawable(String name) {
        return c.getResources().getIdentifier(name, "drawable", c.getPackageName());
    }

    //ищет значение строкоого ресурса по уникальному идентификатору
    public String getValueResource(int num) {
        return c.getResources().getString(num);
    }

    //Поиск текста вопроса для теста
    public String getQuestion() {
        int num = getIdRes(getQuestionName());
        return getValueResource(num);
    }

    //поиск тектов вопросов для теста
    public String[] getAnswers(int quantity) {
        String[] answers_id = new String[quantity];
        String[] answers_name = getAnswersName();

        for (int i=0; i<answers_name.length; i++) {
            int num = getIdRes(answers_name[i]);
            answers_id[i] = getValueResource(num);
        }

        return answers_id;
    }

    //возвращает имя вопроса в xml по его номеру
    private String getQuestionName(){ return "q"+Integer.toString(q_number); }

    //возвращает имена ответов в xml по номеру вопроса и номеру ответа
    private String[] getAnswersName(){
        String[] ans = new String[4];

        for (int i = 0; i < 4; i++) {
            ans[i] = "q"+Integer.toString(q_number)+"a"+Integer.toString(i);
        }

        return ans;
    }

    //возвращает имя правильного ответа
    public int getRightAnswer() {
        String right_num = c.getString(getIdRes("q"+Integer.toString(q_number)+"right"));
        return Integer.parseInt(right_num);
    }

    //возвращает тип анимации по ее номеру
    public String getAnimationtype(int anim_num){
        return c.getString(getIdRes("anim_type"+Integer.toString(anim_num)));
    }

    //возвращает имя файла с анимацией по номеру
    public String getAnimationFileName(int anim_num){
        return c.getString(getIdRes("anim_file"+Integer.toString(anim_num)));
    }

    //возвращает имя файла с гифкой по номеру
    public String getGifFileName(int anim_num){
        return c.getString(getIdRes("gif"+Integer.toString(anim_num)));
    }

    public Drawable getDrawable(int gif_number){
        int num = getIdDrawable(getGifFileName(gif_number));
        return c.getDrawable(num);
    }

}
