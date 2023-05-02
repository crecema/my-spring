package com.crecema.my.spring.common.launcher;

import com.crecema.my.spring.common.service.JokesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class JokesLauncher {

    private final JokesService jokesService;

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            }
            String joke = jokesService.getJoke();
            System.out.println(joke);
        }
        scanner.close();
    }

}
