import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;
import java.time.format.DateTimeFormatter;

class CinemaSeat
{
    private int row;
    private int seatNumber;
    private boolean isOccupied;

    public CinemaSeat(int row, int seatNumber)
    {
        this.row = row;
        this.seatNumber = seatNumber;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void vacate() {
        this.isOccupied = false;
    }

    public String toString() {
        return this.isOccupied ? "X" : "_";
    }
}

class CinemaHall
{
    private int row;
    private int seatsPerRow;
    private List<MovieSession> sessions;
    protected CinemaSeat[][] seats;
    public CinemaHall(int row, int seatsPerRow)
    {
        this.row = row;
        this.seatsPerRow = seatsPerRow;
        this.sessions = new ArrayList<>();
        this.seats = new CinemaSeat[row][seatsPerRow];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                this.seats[i][j] = new CinemaSeat(i + 1, j + 1);
            }
        }
    }

    public int getRow()
    {
        return row;
    }
    public void resetSeats()
    {
        for (CinemaSeat[] row : seats)
            for (CinemaSeat seat : row)
                seat.vacate();
    }
    public int getSeatsPerRow()
    {
        return seatsPerRow;
    }
    public boolean isSeatOccupied(int row, int seatNumber)
    {
        return seats[row - 1][seatNumber - 1].isOccupied();
    }

    public void occupySeat(int row, int seatNumber)
    {
        seats[row - 1][seatNumber - 1].occupy();
    }
    public void addSession(String movieTitle, String startTime, String endTime)
    {
        for(MovieSession session : sessions)
        {
            if(session.getStartTime().equals(startTime))
            {
                System.out.println("Session at " + startTime + " already exists");
                return;
            }
        }

        MovieSession session = new MovieSession(movieTitle, startTime, endTime, this);
        sessions.add(session);
    }

    public List<MovieSession> getSessions()
    {
        return sessions;
    }
    public void vacateSeat(int row, int seatNumber)
    {
        seats[row - 1][seatNumber - 1].vacate();
    }
}

class MovieSession
{
    private String movieName;
    private String startTime;
    //private int durationMinutes;
    private String endTime;
    //private Map<Integer, CinemaHall> halls;
    private CinemaHall cinemaHall;
   public MovieSession(String movieName, String startTime, String endTime, CinemaHall cinemaHall)
    {
        this.movieName = movieName;
        this.startTime = startTime;
        this.endTime = endTime;
        //this.durationMinutes = durationMinutes;
        this.cinemaHall = cinemaHall;
    }

    public String getMovieName()
    {
        return movieName;
    }

    public String getStartTime()
    {
        return startTime;
    }
    public String getEndTime()
    {
        return endTime;
    }

   /* public int getDurationMinutes()
   {
        return durationMinutes;
    }*/

    //public Map<Integer, CinemaHall> getCinemaHalls()
   // {
   //     return halls;
  //  }

    public void printSeatPlan()
    {
        System.out.println("Места в зале: ");
        for (int i = 0; i < cinemaHall.getRow(); i++)
        {
            for (int j = 0; j < cinemaHall.getSeatsPerRow(); j++) {
                if (cinemaHall.seats[i][j].isOccupied())
                {
                    System.out.print("X");
                } else
                {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }
}

class CinemaTheater
{
    private String name;
    int nextHallNumber;
    private Map<Integer, CinemaHall> halls;
    //private List<MovieSession> sessions;
    public CinemaTheater(String name) {
        this.name = name;
        this.halls = new HashMap<>();
        int nextHallNumber = 0;
        //this.sessions = sessions;
    }

    public String getName() {
        return name;
    }

    public void addHall(CinemaHall hall)
    {
        halls.put(nextHallNumber, hall);
        nextHallNumber++;
    }

   /* public List<MovieSession> getSessions()
    {
        return sessions;
    }*/
    public void addSession(String movieTitle, String startTime, String endTime, int hallNumber)
    {
        if (halls.containsKey(hallNumber))
        {
            CinemaHall hall = halls.get(hallNumber);
            for (MovieSession session : hall.getSessions())
            {
                if (areSessionsIntersect(startTime, endTime, session.getStartTime(), session.getEndTime()))
                {
                    System.out.println("Сеанс пересекается с существующим сеансом, начало которого: " + session.getStartTime() + " и конец: " + session.getEndTime());
                }
            }
            hall.addSession(movieTitle, startTime, endTime);
        } else {
            System.out.println("Зал с номером " + hallNumber + " не существует");
        }
    }

    private boolean areSessionsIntersect(String startTime, String endTime, String startTime1, String endTime1)
    {
        LocalDateTime start1 = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end1 = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime start2 = LocalDateTime.parse(startTime1, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end2 = LocalDateTime.parse(endTime1, DateTimeFormatter.ISO_DATE_TIME);
        return (start1.isBefore(end2) && end1.isAfter(start2)) || (start2.isBefore(end1) && end2.isAfter(start1));
    }


    public Map<Integer, CinemaHall> getHalls()
    {
       return halls;
    }
}

class CinemaApp
{

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<CinemaTheater> theaters = new ArrayList<>();

    public static void main(String[] args)
    {
        initializeData();
        displayMenu();
    }

    private static void initializeData()
    {
        // Initialize some test data
        CinemaTheater theater1 = new CinemaTheater("Theater 1");
        CinemaTheater theater2 = new CinemaTheater("Theater 2");

        CinemaHall hall1 = new CinemaHall(10, 20);
        CinemaHall hall2 = new CinemaHall(8, 15);
        theater1.addHall(hall1);
        theater1.addHall(hall2);
        theater1.addSession("CheckPook", "2023-10-24T12:10:00", "2023-10-24T12:55:00", 0);
        theater1.addSession("DS_flash", "2023-10-22T13:10:00", "2023-10-22T15:55:00", 1);
        theaters.add(theater1);

        CinemaHall hall3 = new CinemaHall(12, 18);
        theater2.addHall(hall3);
        theater2.addHall(hall1);
        theater2.addSession("Marvel_Capitan", "2023-10-22T14:13:20", "2023-10-22T16:12:50", 0);
        theaters.add(theater2);
    }

    private static void displayMenu()
    {
        boolean exit = false;
        while (!exit) {
            System.out.println("Выберите действие: [1] Administrator, [2] User, [-1] Выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminMode();
                    break;
                case 2:
                    userMode();
                    break;
                case -1:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Пробуйте снова.");
            }
        }
        scanner.close();
    }

    public static void nextSession()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста, введите название фильма ");
        String movieTitle = scanner.nextLine();

        List<String> nextSessionDetails = findNextSession(theaters, movieTitle);

        // Печать информацию о ближайшей сессии
        if (nextSessionDetails != null && !nextSessionDetails.isEmpty())
        {
            String startTime = nextSessionDetails.get(0);
            String cinemaName = nextSessionDetails.get(1);
            String hallNumberStr = nextSessionDetails.get(2);

            int hallNumber = Integer.parseInt(hallNumberStr);

            System.out.println("Следующий ближайший фильм '" + movieTitle + "' начало которого в "
                    + startTime + " в кинотеатре "
                    + cinemaName + ", Зал "
                    + hallNumber + ".");

            for (CinemaTheater theater : theaters)
            {
                if (theater.getName().equals(cinemaName))
                {
                    if (theater.getHalls().containsKey(hallNumber))
                    {
                        CinemaHall hall = theater.getHalls().get(hallNumber);
                        for (MovieSession session : hall.getSessions())
                        {
                            if (session.getStartTime().equals(startTime))
                            {
                                session.printSeatPlan();
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        else
        {
            System.out.println("Нет доступных сеансов: " + movieTitle + ".");
        }
    }
    private static void adminMode()
    {
        System.out.println("=== Administrator Mode ===");
        System.out.println("Выберите действие: [1] Добавить кинотеатр, [2] Добавить зал, [3] Добавить сеанс, [4] Показать расписание кинотеатра, [5] Показать ближайший сеанс для покупки, [6] Изменить статус кресла на противоположное(занято/свободно) [-1] Назад");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addCinemaTheater();
                break;
            case 2:
                addHall();
                break;
            case 3:
                addSession();
                break;
            case 4:
                showCinemaTheatersAndTheirHallsAndTheisMovieSessions();
                break;
            case 5:
                nextSession();
                break;
            case 6:
                changeSeatStatusAdmin();
                break;
            case -1:
                break;
            default:
                System.out.println("Неверный выбор. Пробуйте снова.");
                adminMode();
        }
    }

    private static void userMode()
    {
        System.out.println("=== User Mode ===");
        System.out.println("Выберите действие: [1] Показать расписание кинотеатра, [2] Купить билет , [3] Показать ближайший сеанс для покупки [-1] Назад");
        int choice = scanner.nextInt();
        switch (choice)
        {
            case 1:
                showCinemaTheatersAndTheirHallsAndTheisMovieSessions();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                nextSession();
                break;
            case -1:
                break;
            default:
                System.out.println("Неверный выбор. Пробуйте снова.");
                userMode();
        }
    }

    private static void showCinemaTheatersAndTheirHallsAndTheisMovieSessions() // Расписание сеансов
    {
        displayTheaters();
        int theaterIndex = scanner.nextInt();
        if (theaterIndex >= 0 && theaterIndex < theaters.size())
        {
            Map<Integer, CinemaHall> halls = theaters.get(theaterIndex).getHalls();
            displayHallsInfo(halls);
            for (Map.Entry<Integer, CinemaHall> entry : halls.entrySet())
            {
                System.out.println("Зал " + entry.getKey());
                for (MovieSession movieSession : entry.getValue().getSessions())
                {
                    System.out.println("---------------------------");
                    System.out.println("Фильм '" + movieSession.getMovieName() + "' Начало: " + movieSession.getStartTime() + " конец: " + movieSession.getEndTime());
                    movieSession.printSeatPlan();
                }
                System.out.println();
            }
        }
    }
    private static void changeSeatStatusAdmin()
    {
        System.out.println("Выберите индекс кинотеатра:");
        displayTheaters();
        int theaterIndex = scanner.nextInt();

        System.out.println("Выберите индекс зала для сеанса:");
        Map<Integer, CinemaHall> halls = theaters.get(theaterIndex).getHalls();
        displayHalls(halls);
        int hallIndex = scanner.nextInt();

        CinemaHall hall = halls.get(hallIndex);

        System.out.println("Введите номер ряда и номер места (через пробел):");
        int row = scanner.nextInt();
        int seat = scanner.nextInt();

        changeSeatStatus(hall, row, seat);

        adminMode();
    }

    private static void changeSeatStatus(CinemaHall hall, int row, int seat)
    {
        if (row > 0 && row <= hall.getRow() && seat > 0 && seat <= hall.getSeatsPerRow())
        {
            if(hall.isSeatOccupied(row, seat)) {
                hall.vacateSeat(row, seat);
                System.out.println("Место " + row + ", " + seat + " освобождено.");
            }
            else {
                hall.occupySeat(row, seat);
                System.out.println("Место " + row + ", " + seat + " занято.");
            }
        }
        else
        {
            System.out.println("Введен неверный номер ряда или места.");
        }
    }

        private static void addCinemaTheater ()
        {
            System.out.println("Введите название кинотеатра: ");
            String name = scanner.next();
            theaters.add(new CinemaTheater(name));
            System.out.println("Кинотеатр '" + name + "' добавлен успешно.");
            adminMode();
        }

        private static void addHall ()
        {
            System.out.println("Выберите индекс кинотеатра, чтобы добавить зал:");
            displayTheaters();
            int theaterIndex = scanner.nextInt();
            if (theaterIndex >= 0 && theaterIndex < theaters.size())
            {
                System.out.println("Введите количество рядов для зала:");
                int rows = scanner.nextInt();
                System.out.println("Введите количество мест в ряду для зала:");
                int seatsPerRow = scanner.nextInt();
                CinemaHall hall = new CinemaHall(rows, seatsPerRow);
                theaters.get(theaterIndex).addHall(hall);
                System.out.println("Зал успешно добавлен.");
            }
            else
            {
                System.out.println("Индекс кинотеатра неверный.");
            }
            adminMode();
        }

        private static void addSession ()
        {
            System.out.println("Выберите индекс кинотеатра, чтобы добавить сеанс: ");
            displayTheaters();
            int theaterIndex = scanner.nextInt();
            if (theaterIndex >= 0 && theaterIndex < theaters.size())
            {
                System.out.println("Выберите индекс зала для сеанса:");
                Map<Integer, CinemaHall> halls = theaters.get(theaterIndex).getHalls();
                displayHalls(halls);
                int hallIndex = scanner.nextInt();
                System.out.println("Введите название фильма:");
                String movieName = scanner.next();
                System.out.println("Введите начало сеанса (yyyy-mm-ddThh:mm:ss):");
                String startTime = scanner.next();
                System.out.println("Введите конец сеанса (yyyy-mm-ddThh:mm:ss):");
                String endTime = scanner.next();

                if (hallIndex >= 0 && hallIndex < halls.size())
                {
                    theaters.get(theaterIndex).addSession(movieName, startTime, endTime, hallIndex);
                    System.out.println("Сеанс успешно добавлен.");
                }
                else
                {
                    System.out.println("Индекс зала неверный.");
                }
            }
            else
            {
                System.out.println("Индекс кинотеатра неверен.");
            }
            adminMode();
        }
   /* private static void viewSchedule()
   {
        System.out.println("Выберите индекс кинотеатра, чтобы просмотреть расписание:");
        displayTheaters();
        int theaterIndex = scanner.nextInt();
        if (theaterIndex >= 0 && theaterIndex < theaters.size())
        {
            CinemaTheater theater = theaters.get(theaterIndex);
            List<MovieSession> sessions = theater.getSessions(); // Работает, если для кинотеатров создать массив всех сессий, а не только для зала
            System.out.println("Schedule for cinema theater '" + theater.getName() + "':");
            System.out.println("-----------------------------------------");
            for (MovieSession session : sessions) {
                System.out.println("Movie: " + session.getMovieName());
                System.out.println("Start Time: " + session.getStartTime());
                System.out.println("End Time: " + session.getEndTime());
                System.out.println("Cinema Hall: " + session.getCinemaHall().getRow() + " rows, " +
                        session.getCinemaHall().getSeatsPerRow() + " seats per row");
                System.out.println("-----------------------------------------");
            }
        }
        else
        {
            System.out.println("Invalid theater index.");
        }
        userMode();
    }*/

    private static void buyTicket()
    {
        System.out.println("Выберите индекс кинотеатра:");
        displayTheaters();
        int theaterIndex = scanner.nextInt();
        if (theaterIndex < 0 || theaterIndex >= theaters.size())
        {
            System.out.println("Неверный индекс кинотеатра.");
            userMode();
            return;
        }
        CinemaTheater theater = theaters.get(theaterIndex);

        Map<Integer, CinemaHall> halls = theater.getHalls();
        System.out.println("Выберите индекс зала кинотеатра:");
        displayHalls(halls);
        int hallIndex = scanner.nextInt();
        if (!halls.containsKey(hallIndex))
        {
            System.out.println("Индекс зала кинотеатра неверный.");
            userMode();
            return;
        }
        CinemaHall hall = halls.get(hallIndex);

        List<MovieSession> sessions = hall.getSessions();
        System.out.println("Выберите индекс сеанса:");
        for (int i = 0; i < sessions.size(); i++)
        {
            System.out.println(i + ". Сеанс: " + sessions.get(i).getMovieName() +
                    ", Начало сеанса: " + sessions.get(i).getStartTime() +
                    ", Конец сеанса: " + sessions.get(i).getEndTime());
        }
        int sessionIndex = scanner.nextInt();
        if (sessionIndex < 0 || sessionIndex >= sessions.size())
        {
            System.out.println("Индекс сеанса неверный.");
            userMode();
            return;
        }
        MovieSession session = sessions.get(sessionIndex);
/*
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime sessionEndTime = LocalDateTime.parse(session.getEndTime(), DateTimeFormatter.ISO_DATE_TIME);
        Duration timeDifference = Duration.between(currentTime, sessionEndTime);

        if (!timeDifference.isNegative())
        {
            hall.resetSeats();
            System.out.println("Сеанс кино закончился. Сиденья установлены заново.");
            userMode();
            return;
        }
*/
        System.out.println("Введите номер ряда и номер места (через пробел):");
        int row = scanner.nextInt();
        int seat = scanner.nextInt();

        if (row > 0 && row <= hall.getRow() && seat > 0 && seat <= hall.getSeatsPerRow())
        {
            if (!hall.isSeatOccupied(row, seat))
            {
                hall.occupySeat(row, seat);
                System.out.println("Билет успешно приобретен на ряд " + row + ", место " + seat + " на фильм " + session.getMovieName() + ".");
            } else
            {
                System.out.println("Место уже занято.");
            }
        }
        else
        {
            System.out.println("Введен неверный номер ряда или места.");
        }
        userMode();
    }


        private static void displayTheaters ()
        {
            System.out.println("Выберите кинотеатр: ");
            for (int i = 0; i < theaters.size(); i++)
            {
                System.out.println(i + ". " + theaters.get(i).getName());
            }
        }

        private static void displayHalls (Map <Integer, CinemaHall > halls)
        {
            System.out.println("Выберите зал: ");
            for (int i = 0; i < halls.size(); i++)
            {
                System.out.println(i + ". " + halls.get(i).getRow() + " рядов, " + halls.get(i).getSeatsPerRow() + " мест в ряду");
            }
        }

    private static void displayHallsInfo (Map <Integer, CinemaHall > halls)
    {
        System.out.println("Информация о залах: ");
        for (int i = 0; i < halls.size(); i++)
        {
            System.out.println(i + ". " + halls.get(i).getRow() + " рядов, " + halls.get(i).getSeatsPerRow() + " мест в ряду");
        }
    }


    public static List<String> findNextSession(List<CinemaTheater> cinemas, String movieTitle)
    {
        List<String> result = new ArrayList<>();
        MovieSession nearestMovie = null;
        int nearestHallNumber = -1;
        String nearestCinema = "";
        LocalDateTime currentTime = LocalDateTime.now();
        Duration shortestDuration = null;
        for (CinemaTheater cinema: cinemas) {
            for (Map.Entry<Integer, CinemaHall> entry: cinema.getHalls().entrySet())
            {
                int hallNumber = entry.getKey();
                CinemaHall hall = entry.getValue();
                for (MovieSession session : hall.getSessions())
                {
                    if (!session.getMovieName().equals(movieTitle))
                    {
                        continue;
                    }

                    boolean availableSeat = false;
                    for (int i = 0; i < hall.getRow() && !availableSeat; i++)
                    {
                        for (int j = 0; j < hall.getSeatsPerRow(); j++)
                        {
                            if (!hall.isSeatOccupied(i + 1, j + 1))
                            {
                                availableSeat = true;
                                break;
                            }
                        }
                    }
                    if (!availableSeat)
                    {
                        continue;
                    }
                    LocalDateTime sessionStartTime = LocalDateTime.parse(session.getStartTime());
                    Duration duration = Duration.between(currentTime, sessionStartTime);
                    if (duration.isNegative())
                    {
                        continue;
                    }
                    if (shortestDuration == null || duration.compareTo(shortestDuration) < 0)
                    {
                        shortestDuration = duration;
                        nearestMovie = session;
                        nearestCinema = cinema.getName();
                        nearestHallNumber = hallNumber;
                    }
                }
            }
        }
        result.add(nearestMovie.getStartTime());
        result.add(nearestCinema);
        result.add(String.valueOf(nearestHallNumber));
        return result;
    }


/*
    private static Date parseDateTime(String dateTimeStr)
    {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.parse(dateTimeStr);
        } catch (ParseException e)
        {
            System.out.println("Invalid datetime format.");
        }
        return null;
    }*/
}

