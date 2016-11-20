package com.example.rest.checklist;

import com.example.rest.web.CheckListSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by czrif on 11/8/2016.
 */
public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    List<CheckListSummary> findAllBy();

}
