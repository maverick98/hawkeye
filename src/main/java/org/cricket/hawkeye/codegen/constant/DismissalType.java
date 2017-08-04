package org.cricket.hawkeye.codegen.constant;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.cricket.hawkeye.codegen.annotation.AggregateClause;
import org.cricket.hawkeye.codegen.annotation.ComparisionClause;
import org.cricket.hawkeye.codegen.annotation.HQLGenerate;

/**
 *
 * @author msahu
 */
public class DismissalType implements Externalizable,Comparable<DismissalType> {

    @HQLGenerate(comparision=@ComparisionClause(equal=true),aggregate=@AggregateClause)
    private String type;

    public DismissalType() {
    }

    public DismissalType(String type){
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(type);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.type = (String) in.readObject();
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public int compareTo(DismissalType otherDismissalType) {
        return this.getType().compareTo(otherDismissalType.getType());
    }



    
}
