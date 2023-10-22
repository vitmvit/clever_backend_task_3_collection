package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    /**
     * из представленных животных отобрать все молодые особи от 10 до 20 лет и отсортировать по возрасту
     * (по возрастанию) далее - распределить по 7 на каждый зоопарк. Зоопарков неограниченное кол-во а вы - директор
     * 3-го по счёту зоопарка. Полученных животных вывести в консоль.
     */
    public static List<Animal> task1() {
        int zooParkIndex = 3; // индекс нашего зоопарка,
        int startIndex = (zooParkIndex - 1) * 7;
        int endIndex = startIndex + 7;
        List<Animal> animals = Util.getAnimals();
        List<Animal> res = animals.stream().filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .limit(endIndex)
                .skip(startIndex).toList();
        res.forEach(System.out::println);
        return res;
    }

    /**
     * берет всех животных из Японии (Japanese) и записать породу UPPER_CASE в если пол Female преобразовать к строкам
     * породы животных и вывести в консоль
     */
    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> animal.getOrigin().equals("Japanese"))
                .map(animal -> {
                    if (animal.getGender().equals("Female")) {
                        return animal.getBread().toUpperCase();
                    }
                    return animal.getBread();
                })
                .toList()
                .forEach(System.out::println);
    }

    /**
     * берет всех животных старше 30 лет и вывести все страны происхождения без дубликатов начинающиеся на "A"
     */
    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin -> origin.startsWith("A"))
                .toList()
                .forEach(System.out::println);
    }

    /**
     * подсчитывает количество всех животных пола = Female
     */
    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count();
        System.out.println(count);
    }

    /**
     * берет всех животных возрастом 20 - 30 лет и узнает, есть ли среди нах хоть один из страны Венгрия (Hungarian)
     */
    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        boolean req = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> animal.getOrigin().equals("Hungarian"));
        System.out.println(req);
    }

    /**
     * берет всех животных и узнает, все ли они пола Male и Female
     */
    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        boolean req = animals.stream()
                .allMatch(animal -> animal.getGender().equals("Female") || animal.getGender().equals("Male"));
        System.out.println(req);
    }

    /**
     * берет всех животных и узнает что ни одно из них не имеет страну происхождения Oceania
     */
    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        boolean req = animals.stream()
                .noneMatch(animal -> animal.getOrigin().equals("Oceania"));
        System.out.println(req);
    }

    /**
     * берет всех животных и сортирует их породу в стандартном порядке и взять первые 100. Выводит в консоль возраст
     * самого старого животного
     */
    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        OptionalInt max = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max();
        System.out.println(max.getAsInt());
    }

    /**
     * берет всех животных и преобразовывает их в породы, а породы в []char. Выводит в консоль длину самого короткого
     * массива
     */
    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        OptionalInt min = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(chars -> chars.length)
                .min();
        System.out.println(min.getAsInt());
    }

    /**
     * берет всех животных и подсчитывает суммарный возраст всех животных. Выводит результат в консоль
     */
    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        int totalAge = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println(totalAge);
    }

    /**
     * берет всех животных и подсчитывает средний возраст всех животных из индонезии (Indonesian). Выводит результат в
     * консоль
     */
    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        OptionalDouble averageAge = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average();
        System.out.println(averageAge.getAsDouble());
    }

    /**
     * во Французский легион принимают людей со всего света, но есть отбор по полу (только мужчины) возраст от 18 до
     * 27 лет. Преимущество отдаётся людям военной категории 1, на втором месте - военная категория 2, и на третьем
     * месте военная категория 3. Отсортировать всех подходящих кандидатов в порядке их приоритета по военной категории.
     * Однако взять на обучение академия может только 200 человек. Вывести их в консоль.
     */
    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> {
                    LocalDate now = LocalDate.now();
                    LocalDate age18 = now.minusYears(18);
                    LocalDate age27 = now.minusYears(27);
                    return person.getDateOfBirth().isBefore(age18) && person.getDateOfBirth().isAfter(age27);
                })
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .toList()
                .forEach(System.out::println);
    }

    /**
     * надвигается цунами и в районе эвакуации требуется в первую очередь обойти дома и эвакуировать больных и раненых
     * (из Hospital), во вторую очередь детей и стариков (до 18 и пенсионного возраста) а после всех остальных. В
     * первый этап эвакуации мест в эвакуационном транспорте только 500. Вывести всех людей попадающих в первый этап
     * эвакуации в порядке приоритета (в консоль).
     */
    public static void task13() {
        List<House> houses = Util.getHouses();
        List<Person> persons = houses.stream()
                .flatMap(house -> house.getPersonList().stream()).toList()
                .stream()
                .filter(person -> {
                    LocalDate now = LocalDate.now();
                    LocalDate age18 = now.minusYears(18);
                    LocalDate age60 = now.minusYears(60);
                    return person.getDateOfBirth().isAfter(age18) || person.getDateOfBirth().isBefore(age60);
                }).toList();

        List<Person> hospitalPerson = houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .flatMap(house -> house.getPersonList().stream()).toList();

        Stream.concat(persons.stream(), hospitalPerson.stream())
                .limit(500)
                .toList()
                .forEach(System.out::println);
    }

    /**
     * из перечня автомобилей приходящих на рынок Азии логистическому агентству предстоит отсортировать их в порядке
     * следования 1.Туркменистан - 2.Узбекистан - 3.Казахстан - 4.Кыргызстан - 5.Россия - 6.Монголия. Все автомобили
     * марки "Jaguar" а так же все авто цвета White идут в первую страну. Из оставшихся все автомобили с массой до
     * 1500 кг и марок BMW, Lexus, Chrysler и Toyota идут во второй эшелон. Из оставшихся все автомобили Черного цвета
     * с массой более 4000 кг и все GMC и Dodge идут в третий эшелон. Из оставшихся все автомобили года выпуска до 1982
     * или все модели "Civic" и "Cherokee" идут в четвёртый эшелон. Из оставшихся все автомобили цветов НЕ Yellow, Red,
     * Green и Blue или же по стоимости дороже 40000 в пятый эшелон Из оставшиеся все автомобили в vin номере которых
     * есть цифра "59" идут в последний шестой эшелон. Оставшиеся автомобили отбрасываем, они никуда не идут. Измерить
     * суммарные массы автомобилей всех эшелонов для каждой из стран и подсчитать сколько для каждой страны будет стоить
     * транспортные расходы если учесть что на 1 тонну транспорта будет потрачено 7.14 $. Вывести суммарные стоимости в
     * консоль. Вывести общую выручку логистической кампании.
     */
    public static void task14() {
        List<Car> cars = Util.getCars();
        double transportCostPerTon = 7.14;
        Map<String, List<Car>> carsByCountry = cars.stream()
                .collect(Collectors.groupingBy(car -> {
                    if (car.getCarMake().equals("Jaguar") || car.getColor().equals("White")) {
                        return "Туркменистан";
                    } else if (Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake()) && car.getMass() <= 1500) {
                        return "Узбекистан";
                    } else if (Arrays.asList("GMC", "Dodge").contains(car.getCarMake()) || car.getColor().equals("Black") && (car.getMass() > 4000)) {
                        return "Казахстан";
                    } else if (car.getReleaseYear() <= 1982 || car.getCarModel().equals("Civic") || car.getCarModel().equals("Cherokee")) {
                        return "Кыргызстан";
                    } else if (!Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) || car.getPrice() > 40000) {
                        return "Россия";
                    } else if (car.getVin().contains("59")) {
                        return "Монголия";
                    } else {
                        return "";
                    }
                }));
        Map<String, Integer> totalMassByCountry = new HashMap<>();
        Map<String, Double> totalTransportCostByCountry = new HashMap<>();
        carsByCountry.forEach((country, countryCars) -> {
            if (!country.isEmpty()) {
                int totalMass = countryCars.stream()
                        .mapToInt(Car::getMass)
                        .sum();
                totalMassByCountry.put(country, totalMass);

                double transportCost = (totalMass / 1000.0) * transportCostPerTon;
                totalTransportCostByCountry.put(country, transportCost);
            }
        });

        double totalRevenue = totalTransportCostByCountry.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        totalTransportCostByCountry.forEach((country, transportCost) -> {
            System.out.println("Страна: " + country);
            System.out.println("Суммарная масса автомобилей: " + totalMassByCountry.get(country));
            System.out.println("Стоимость транспортных расходов: " + transportCost);
            System.out.println();
        });

        System.out.println("Общая выручка логистической компании: " + totalRevenue);

    }


    /**
     * для оранжереи нужно подобрать растения соответствующие требованиям. Во-первых, нужно произвести сложную
     * сортировку каталога растений. Отсортировать их по странам происхождения в обратном порядке После по стоимости и
     * еще по водопотреблению в обратном порядке. Из этого списка взять растения название которых от буквы "S" до буквы
     * "C". Если растения тенелюбивые и им подходит горшок из стекла, алюминия или стали - то выбираем их. Далее на
     * каждое растение надо рассчитать стоимость растения + стоимость потребления воды за 5 лет c учётом того что
     * кубометр воды стоит 1.39 $. Суммировать общую стоимость обслуживания всех растений. Во сколько это обойдётся
     * бюджету?
     */
    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        double waterCostPerCubicMeter = 1.39;
        int wateringPeriodInYears = 5;

        double res = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Comparator.comparing(Flower::getPrice).reversed())
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> Arrays.asList("A", "S", "C", "D").contains(String.valueOf(flower.getCommonName().charAt(0)).toUpperCase()))
                .filter(flower -> flower.isShadePreferred() && flower.getFlowerVaseMaterial().containsAll(Arrays.asList("Glass", "Aluminum", "Steel")))
                .mapToDouble(flower -> {
                    double wateringCost = flower.getWaterConsumptionPerDay() * 365 * wateringPeriodInYears / 1000.0 * waterCostPerCubicMeter;
                    return flower.getPrice() + wateringCost;
                })
                .sum();
        System.out.println("Общая стоимость обслуживания всех растений: " + res);
    }

    /**
     * выводит список студентов младше 18 лет в алфавитном порядке с указанием возраста
     */
    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname)).toList()
                .forEach(student -> {
                    System.out.println(student.getSurname() + ", возраст: " + student.getAge());
                });
    }

    /**
     * выводит список групп (без повторений)
     */
    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * определяет средний возраст студентов для каждого факультета. Выводит название факультета и средний возраст в
     * порядке убывания возраста
     */
    public static void task18() {
        List<Student> students = Util.getStudents();
        Map<String, Double> averageAgeByFaculty = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(Student::getAge)));
        averageAgeByFaculty.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Факультет: " + entry.getKey() + ", Средний возраст: " + entry.getValue()));
    }

    /**
     * выводит список студентов заданной группы, у которых сдан 3 экзамен (>4)
     */
    public static void task19() {
        List<Student> students = Util.getStudents();
        String groupName = "C-2";

        students.stream()
                .filter(student -> student.getGroup().equals(groupName))
                .filter(student -> {
                    Examination exam3 = Util.getExaminations().stream()
                            .filter(exam -> exam.getStudentId() == student.getId())
                            .findFirst()
                            .orElse(null);
                    return exam3 != null && exam3.getExam3() > 4;
                })
                .forEach(student -> System.out.println(student.getSurname()));
    }

    /**
     * определяет факультет с максимальной средней оценкой по первому экзамену
     */
    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        Map<String, Double> averageGradeByFaculty = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(student -> {
                    Examination exam = examinations.stream()
                            .filter(e -> e.getStudentId() == student.getId())
                            .findFirst()
                            .orElse(null);
                    return (exam != null) ? exam.getExam1() : 0.0;
                })));

        String facultyWithMaxAverageGrade = Collections.max(averageGradeByFaculty.entrySet(), Map.Entry.comparingByValue())
                .getKey();

        System.out.println("Факультет с максимальной средней оценкой по первому экзамену: " + facultyWithMaxAverageGrade);
    }

    /**
     * определяет количество студентов в каждой группе
     */
    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()))
                .forEach((group, count) -> System.out.println("Группа: " + group + ", Количество студентов: " + count));
    }

    /**
     * определяет минимальный возраст для каждого факультета
     */
    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.mapping(Student::getAge, Collectors.minBy(Integer::compareTo))))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().orElse(null)))
                .forEach((faculty, minAge) -> System.out.println("Факультет: " + faculty + ", Минимальный возраст: " + minAge));
    }
}
