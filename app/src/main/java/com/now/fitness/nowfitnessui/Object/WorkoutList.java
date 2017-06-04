package com.now.fitness.nowfitnessui.Object;

/**
 * Created by Maycor Gerilla on 5/29/2017.
 */

public class WorkoutList {

    private String categoryCode;
    private String exerciseName;
    private int repetitions;
    private int exerciseId;
    private boolean withEquipment;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public boolean isWithEquipment() {
        return withEquipment;
    }

    public void setWithEquipment(boolean withEquipment) {
        this.withEquipment = withEquipment;
    }
}
