package entity;

public class PersonModel {
    private final String gender;   // Пол
    private final int age;         // Возраст
    private final String lastName; // Фамилия

    private PersonModel(Builder builder) {
        this.gender = builder.gender;
        this.age = builder.age;
        this.lastName = builder.lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person [Gender=" + gender + ", Age=" + age + ", Last Name=" + lastName + "]";
    }

    public static class Builder {
        private String gender;
        private int age;
        private String lastName;

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonModel build() {
            return new PersonModel(this);
        }
    }
}
