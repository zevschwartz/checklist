package com.example.checklist;

import com.example.checklist.web.CheckListSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by czrif on 11/8/2016.
 */
public interface CheckListRepo extends JpaRepository<CheckList, Long> {

    List<CheckListSummary> findAllBy();

}
