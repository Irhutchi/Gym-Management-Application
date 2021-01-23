

public class Assessment {
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;

    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
    {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
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


    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        if(this.hips >= this.waist){
            this.hips = hips;
        }else {

        }
    }

    /**
     * Builds a String representation of the member assessment
     * @return Details of the specific member assessment
     */
    @Override
    public String toString() {
        return "Assessment details: " + "\t" + " "
                + ", Weight: " + weight
                + ", Chest: " + chest
                + ", Thigh: "  + thigh
                + ", Upper Arm: " +upperArm
                + ", Waist: " + waist
                + ", hips: " + hips;
    }

}