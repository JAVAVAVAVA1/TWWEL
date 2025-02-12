package main.lunch.run;

import main.lunch.aggregate.ClosedDays;
import main.lunch.aggregate.MenuTag;
import main.lunch.aggregate.Store;
import main.lunch.aggregate.StoreStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private static final StoreService ss = new StoreService();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("===== 오늘의 점심 메뉴 추천 <오점뭐?> =====");
            System.out.println("1. 가게 정보 등록");
            System.out.println("2. 가게 정보 수정");
            System.out.println("3. 가게 정보 삭제");
            System.out.println("4. 점심 메뉴 추천");
            System.out.println("5. 프로그램 종료");
            System.out.println("메뉴를 선택해 주세요: ");
            String input = br.readLine();

            switch (input) {
                case 1:
                    ss.insertStoreInfo();
                    break;
                case 2:
                    Store selected =
                    ss.updateStoreInfo(uploadStoreInfo());
                    break;
                case 3:
                    ss.deleteStoreInfo();
                    break;
                case 4:
                    ss.giveLunchMenu();
                    break;
                case 5:
                    System.out.println("프로그램을 이용해 주셔서 감사합니다. ");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;

            }
        }
    }


    /* 설명. 가게 정보 입력 메소드*/

    private static Store uploadStoreInfo() throws IOException {
        Store store = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("등록할 가게의 이름을 입력하시오: ");
        String storeName = br.readLine();

        System.out.println("등록할 가게의 대표 메뉴를 입력하시오: ");
        String storeMenu = br.readLine();

        System.out.println("등록할 가게의 위치를 입력하시오: ");
        String storeLocation = br.readLine();

        System.out.println("등록할 가게의 메뉴 가격을 입력하시오: ");
        int menuPrice = Integer.parseInt(br.readLine());

        System.out.println("등록할 가게의 인원 수용량을 입력하시오: ");
        int maxPeople = Integer.parseInt(br.readLine());

        System.out.println("등록할 가게의 휴뮤일을 입력하시오(영어로 입력): ");
        System.out.println("일: SUN, 월: MON, 화: TUE, 수: WED, 목: THR, 금: FRI, 토: SAT");
        String closedDays = br.readLine().toUpperCase();

        ClosedDays cd = switch (closedDays) {
            case "SUN" -> ClosedDays.SUN;
            case "MON" -> ClosedDays.MON;
            case "TUE" -> ClosedDays.TUE;
            case "WED" -> ClosedDays.WED;
            case "THR" -> ClosedDays.THR;
            case "FRI" -> ClosedDays.FRI;
            case "SAT" -> ClosedDays.SAT;
            default -> null;
        };

        System.out.println("등록할 가게의 메뉴 태그 종류를 입력하시오(영어로 입력): ");

        System.out.println("버거: BURGER, 샌드위치: SANDWICH,베트남: VIETNAM,중국음식: CHINA" +
                        ",한국음식: KOREA,피자: PIZZA,분식: SNACK");
        String menuTag = br.readLine().toUpperCase();

        MenuTag mt = switch (menuTag) {
            case "BURGER" -> MenuTag.BURGER;
            case "SANDWICH" -> MenuTag.SANDWICH;
            case "VIETNAM" -> MenuTag.VIETNAM;
            case "CHINA" -> MenuTag.CHINA;
            case "KOREA" -> MenuTag.KOREA;
            case "PIZZA" -> MenuTag.PIZZA;
            case "SNACK" -> MenuTag.SNACK;
            default -> null;
        };


        StoreStatus storeStatus = new storeStatus();
        store = new Store(storeName, storeMenu, storeLocation, menuPrice, maxPeople, closedDays, menuTag, storeStatus.OPEN);

        return store;
    }

    /* 설명. 수정하고 싶은 가게 메뉴명 입력받는 메소드*/
    private static String chooseStoreNumber() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("수정하고 싶은 가게 명을 입력하시오: ");

        return br.readLine();
    }

    private static Store reform(Store modifyStore) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("===== 가게 정보 중에 수정하고 싶은 내용의 번호를 입력하세요 =====");
        System.out.println("1. 가게 상호 명");
        System.out.println("2. 가게 대표 음식");
        System.out.println("3. 가게 위치");
        System.out.println("4. 음식 가격");
        System.out.println("5. 최대 수용 인원");
        System.out.println("6. 휴무일");
        System.out.println("7. 메뉴 태그 타입");
        System.out.println("8. 메인으로 돌아가기");

    }
}
