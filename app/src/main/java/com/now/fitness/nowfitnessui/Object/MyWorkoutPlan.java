package com.now.fitness.nowfitnessui.Object;

/**
 * Created by Romeric Heredia on 6/06/2017.
 */

public class MyWorkoutPlan {
    private int myWorkoutPlanId;
    private String myWorkoutPlanName;
    private  int numberOfWorkouts;

    public int getMyWorkoutPlanId() {
        return myWorkoutPlanId;
    }

    public void setMyWorkoutPlanId(int myWorkoutPlanId) {
        this.myWorkoutPlanId = myWorkoutPlanId;
    }

    public String getMyWorkoutPlanName() {
        return myWorkoutPlanName;
    }

    public void setMyWorkoutPlanName(String myWorkoutPlanName) {
        this.myWorkoutPlanName = myWorkoutPlanName;
    }

    public int getNumberOfWorkouts() {
        return numberOfWorkouts;
    }

    public void setNumberOfWorkouts(int numberOfWorkouts) {
        this.numberOfWorkouts = numberOfWorkouts;
    }
}
