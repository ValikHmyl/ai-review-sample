package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskStatsResponse;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeTaskStatsService {

    @Autowired
    private EntityManager em;

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> search(String keyword, String status, String owner) {
        try {
            String q = "SELECT t FROM Task t WHERE 1=1";
            if (keyword != null) {
                q = q + " AND t.title LIKE '%" + keyword + "%'";
            }
            if (status != null) {
                q = q + " AND t.status = '" + status + "'";
            }
            if (owner != null) {
                q = q + " AND t.owner.username = '" + owner + "'";
            }

            Query query = em.createQuery(q);
            List r = query.getResultList();

            List<Task> out = new ArrayList<>();
            int c = 0;
            for (Object o : r) {
                Task t = (Task) o;
                if (t != null) {
                    if (t.getStatus() != null) {
                        if (t.getTitle() != null) {
                            if (t.getTitle().length() > 0) {
                                out.add(t);
                                c = c + 1;
                                if (c >= 100) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return out;
        } catch (Exception e) {
            System.out.println("search failed: " + e);
            return null;
        }
    }

    public TaskStatsResponse completionRate() {
        List<Task> all = taskRepository.findAll();
        int done = 0;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getStatus() != null && all.get(i).getStatus().name().equals("DONE")) {
                done = done + 1;
            }
        }
        TaskStatsResponse r = new TaskStatsResponse();
        r.total = all.size();
        r.done = done;
        if (all.size() == 0) {
            r.rate = 0;
        } else {
            r.rate = (double) done / all.size() * 100;
        }
        return r;
    }
}
