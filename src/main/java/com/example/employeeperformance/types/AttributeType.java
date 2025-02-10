package com.example.employeeperformance.types;

public enum AttributeType {

    PONCTUALITY("Ponctuality", "Measure how much ponctual the employee is."),
    WORK_DELIVERY("Work Delivery", "Measure how satisfactory the employee's work delivery is."),
    PPE_USAGE("PPE Usage", "Measure how satisfactory the employee's PPE usage is."),
    EVOLUTION("Evolution", "Measure how the employee's evolution is going."),
    COMMITMENT("Commitment", "Measure employee's commitment level.");

    String name;
    String description;

    AttributeType(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
