import java.util.*;

enum Priority {
    HIGH, MED, LOW
}

enum Status {
    IN_QUEUE, ASSIGNED, IN_PROGRESS
}

class Task {
    private String project;
    private String description;
    private String assignee;
    private Priority priority;
    private Status status;

    public Task(String project, String description, String assignee, Priority priority) {
        this.project = project;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.status = Status.IN_QUEUE;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return project.equals(task.project) && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, description);
    }
}

class TaskData {
    private Set<Task> annsTasks = new HashSet<>();
    private Set<Task> bobsTasks = new HashSet<>();
    private Set<Task> carolsTasks = new HashSet<>();

    public void addTask(String assignee, Task task) {
        if ("ann".equals(assignee)) {
            annsTasks.add(task);
        } else if ("bob".equals(assignee)) {
            bobsTasks.add(task);
        } else if ("carol".equals(assignee)) {
            carolsTasks.add(task);
        }
    }

    public Set<Task> getTasks(String assignee) {
        if ("ann".equals(assignee)) {
            return annsTasks;
        } else if ("bob".equals(assignee)) {
            return bobsTasks;
        } else if ("carol".equals(assignee)) {
            return carolsTasks;
        } else if ("all".equals(assignee)) {
            Set<Task> allTasks = new HashSet<>();
            allTasks.addAll(annsTasks);
            allTasks.addAll(bobsTasks);
            allTasks.addAll(carolsTasks);
            return allTasks;
        } else {
            throw new IllegalArgumentException("Invalid assignee: " + assignee);
        }
    }

    public Set<Task> getUnassignedTasks() {
        Set<Task> unassignedTasks = new HashSet<>();
        unassignedTasks.addAll(annsTasks);
        unassignedTasks.addAll(bobsTasks);
        unassignedTasks.addAll(carolsTasks);

        Set<Task> assignedTasks = new HashSet<>();
        assignedTasks.addAll(getTasks("all"));

        assignedTasks.removeAll(unassignedTasks);

        return unassignedTasks;
    }

    public Set<Task> getDuplicateAssignedTasks() {
        Set<Task> allTasks = getTasks("all");
        Set<Task> uniqueTasks = new HashSet<>();
        Set<Task> duplicateTasks = new HashSet<>();

        for (Task task : allTasks) {
            if (!uniqueTasks.add(task)) {
                duplicateTasks.add(task);
            }
        }

        return duplicateTasks;
    }
}

public class Main {
    public static void main(String[] args) {
        TaskData taskData = new TaskData();

        // Sample tasks
        Task task1 = new Task("ProjectA", "Implement feature X", "ann", Priority.HIGH);
        Task task2 = new Task("ProjectB", "Fix bug Y", "bob", Priority.MED);
        Task task3 = new Task("ProjectC", "Write documentation", "carol", Priority.LOW);


        taskData.addTask("ann", task1);
        taskData.addTask("bob", task2);
        taskData.addTask("carol", task3);



        Set<Task> allTasks = taskData.getTasks("all");
        for (Task task : allTasks) {
            System.out.println("Project: " + task.getProject());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Assignee: " + task.getAssignee());
            System.out.println("Priority: " + task.getPriority());
            System.out.println("Status: " + task.getStatus());
            System.out.println();
        }


    }
}

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String text = "Carroll began writing the manuscript of the story the next day, although that earliest version is lost. " +
                "The girls and Carroll took another boat trip a month later, when he elaborated the plot to the story of Alice, " +
                "and in November he began working on the manuscript in earnest. To add the finishing touches he researched " +
                "natural history in connection with the animals presented in the book and then had the book examined " +
                "by other children—particularly those of George MacDonald. Though Carroll did add his own illustrations " +
                "to the original copy, on publication he was advised to find a professional illustrator so the pictures " +
                "were more appealing to its audiences. He subsequently approached John Tenniel to reinterpret " +
                "Carroll's visions through his own artistic eye, telling him that the story had been well liked by the" +
                " children.\n" +
                "\n" +
                "Carroll began planning a print edition of the Alice story in 1863. " +
                "He wrote on 9 May 1863 that MacDonald's family had suggested he publish Alice." +
                " A diary entry for 2 July says that he received a specimen page of the print edition around that date. " +
                "On 26 November 1864, Carroll gave Alice the manuscript of Alice's Adventures Under Ground, with illustrations " +
                "by Carroll, dedicating it as \"A Christmas Gift to a Dear Child in Memory of a Summer's Day\"." +
                " The published version of Alice's Adventures in Wonderland is about twice the length of " +
                "Alice's Adventures Under Ground and includes episodes, such as the Mad Tea-Party, " +
                "that did not appear in the manuscript. The only known manuscript copy of Under Ground " +
                "is held in the British Library. Macmillan published a facsimile of the manuscript in 1886.";


        String cleanedText = text.replaceAll("[.!?,\"]", "");


        String[] words = cleanedText.toLowerCase().split("\\s+");


        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));


        List<String> sortedUniqueWords = new ArrayList<>(uniqueWords);
        Collections.sort(sortedUniqueWords);


        for (String word : sortedUniqueWords) {
            System.out.println(word);
        }
    }
}

