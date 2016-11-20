package com.example.rest.checklist;

import com.example.rest.web.CheckListSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by czrif on 11/8/2016.
 */
public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    @Query("select c from CheckList c where c.owner.id = :userid")
    List<CheckListSummary> findAllByOwner(@Param("userid") Long userid);

}
