package com.example.multi;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class TestService {

    private final TestRepository repo;

    private final int DATA_MAXIMUM = 500;

    public TestService(TestRepository repo) {
        this.repo = repo;
    }

    static int compare(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        if (l1 != l2) {
            return l1 - l2;
        }

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < l1; i++) {
            char c = s1.charAt(i);
            if (c >= '0' && c <= '9') {
                sum1 += (c - '0');
            }
        }

        for (int i = 0; i < l2; i++) {
            char c = s2.charAt(i);
            if (c >= '0' && c <= '9') {
                sum2 += (c - '0');
            }
        }

        if (sum1 != sum2) {
            return sum1 - sum2;
        }

        return s1.compareTo(s2);
    }

    @Async("Executor")
    public CompletableFuture<List<String>> serialSort() {
        try {

            List<DataRecord> data = repo.findAll();
            ArrayList<String> res = new ArrayList<>();
            if (data.isEmpty()) {
                res.add("no data~~!");
            } else {
                int fin = data.toArray().length;
                for (int i = 0; i < fin; i++) {
                    res.add(data.get(i).getName());
                }
                res.sort(TestService::compare);
                return CompletableFuture.completedFuture(res);
            }
        } catch (Exception e) {
            CompletableFuture<List<String>> failed = new CompletableFuture<>();
            failed.completeExceptionally(e);
            return failed;
        }
        return null;
    }

    @Async("Executor")
    public void deleteData() {
        repo.deleteAll();
    }

    @Async("Executor")
    public void randomData() {
        Random random = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < DATA_MAXIMUM; i++) {
            int length = 3 + random.nextInt(48);

            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }

            repo.post(sb.toString());
        }
    }
}
