package service.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public abstract class ExternalizableObject implements Externalizable {

    private final long serialVersionUID;

    public ExternalizableObject() {
        this.serialVersionUID = new Random().nextLong();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        try {
            var fields = this.getClass().getDeclaredFields();
            for (int i=0; i< fields.length;i++) {
                var f = fields[i];
                Object value = f.get(this);
                String formattedValue = value.toString();
                String data = "\n" + f.getName() + ": " + formattedValue;
                out.write( data.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            String line;
            while ( (line = in.readLine()) != null ) {

                var parts = line.split(":");
                Field f;
                try {
                    f = this.getClass().getDeclaredField(parts[0].trim());
                }catch (NoSuchFieldException ex){
                    continue;
                }
                f.setAccessible(true);
                switch (f.getType().getName()) {
                    case "java.lang.String":
                        f.set(this, parts[1].trim());
                        break;
                    case "int":
                    case "Integer":
                        f.set(this, Integer.parseInt(parts[1].trim()));
                        break;
                    case "float":
                    case "Float":
                        f.set(this, Float.parseFloat(parts[1].trim()));
                        break;
                    case "long":
                    case "Long":
                        f.set(this, Long.parseLong(parts[1].trim()));
                        break;
                    default:continue;

                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
