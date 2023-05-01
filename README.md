## Running a project from the console

1. Create jar file using command:
```shell
mvn clean install
```
2. Run project in dir where contain `.jar` file(def - `/target`):
```shell
java -jar test-0.0.1-SNAPSHOT.jar true
```

## Command what you can use: 
* `help` - to find available commands;
* `Who is head of department <department_name>` - get the name of the Head of Department;
* `Show <department_name> statistics` - get the number of assistants, associate professors, and professors in the department;
* `Show the average salary for the department <department_name>` - get the average salary for the department;
* `Show count of employee for <department_name>` - get count of employee;
* `Global search by <template>` - find employee by template;
* `exit` - close program.
