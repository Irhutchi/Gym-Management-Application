

public class Assessment {
    private double weight;
    private double thigh;
    private double waist;
    private String comment;

    public Assessment(double weight, double chest, double thigh, double waist, double hips)
    {
        this.weight = weight;
        this.thigh = thigh;
        this.waist = waist;
        this.comment = comment;
    }



    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if ((weight >= 35.0) && (weight <= 250.0)) {
            this.weight = weight;
        } else {
            this.weight = 100.0f;
        }
    }

    public void getThigh(double thigh) {
        if(this.thigh <= this.waist){
            this.thigh = thigh;
        }else {
            this.thigh = waist - 10;
        }
    }


    public double getThigh() {
        return thigh;
    }

    public double getWaist() {
        return waist;
    }

    public String getComment() { return comment; }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public void setComment(String comment) { this.comment = comment; }


    /**
     * Builds a String representation of the member assessment
     * @return Details of the specific member assessment
     */
    @Override
    public String toString() {
        return "Assessment details: " + "\t" + " "
                + ", Weight: " + weight
                + ", Thigh: "  + thigh
                + ", Waist: " + waist
                + ", comment: " + comment;
    }

}