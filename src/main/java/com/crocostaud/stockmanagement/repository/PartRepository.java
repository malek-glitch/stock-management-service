package com.crocostaud.stockmanagement.repository;

import com.crocostaud.stockmanagement.model.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {
    @Query("""
             select p from part p
             where upper(p.ref) like upper(concat('%', ?1, '%'))
         
                or upper(p.name) like upper(concat('%', ?1, '%'))
            """)
    List<Part> findByText(String text);

    @Query("""
            select p from part p
            where p.category.id = ?1
              and (upper(p.ref) = upper(?2) or upper(p.name) = upper(?2))
            """)
    List<Part> findByCategory(Long id, @Nullable String text);

    @Query("""
            select p from part p
            left join p.originals.compatibleSubmodels compatibleSubModels
            where compatibleSubModels.id = ?1
            and  (upper(p.ref) like upper(concat('%', ?2, '%'))
                or SOUNDEX(p.name) = SOUNDEX(?2)
                or upper(p.name) like upper(concat('%', ?2, '%')))
            """)
    List<Part> findBySubmodel(Long id, @Nullable String text);

    @Query("""
            select p from part p
            left join p.originals.compatibleSubmodels compatibleSubModels
            where compatibleSubModels.id = ?1
                        
            """)
    List<Part> findBySubmodel(Long id);

    @Query("""
            select p from part p
            inner join p.originals.compatibleModels compatibleModels
            where compatibleModels.id = ?1
            and  (upper(p.ref) like upper(concat('%', ?2, '%'))
                or SOUNDEX(p.name) = SOUNDEX(?2)
                or upper(p.name) like upper(concat('%', ?2, '%')))
            """)
    List<Part> findByModel(Long id, @Nullable String text);

    @Query("""
            select p from part p
            inner join p.originals.compatibleModels compatibleModels
            where compatibleModels.id = ?1
            """)
    List<Part> findByModel(Long id);

    @Query("""
            select p from part p
            inner join p.originals.compatibleModels compatibleModels
            where p.category.id = ?1 and compatibleModels.id = ?2""")
    List<Part> findByCategoryAndModel(Long id, Long id1);

    @Query("""
            select p from part p
            inner join p.originals.compatibleSubmodels compatibleSubmodels
            where p.category.id = ?1 and compatibleSubmodels.id = ?2""")
    List<Part> findByCategoryAndSubmodel(Long id, Long id1);
}