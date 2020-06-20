/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wepa20.entities.Account;
import wepa20.repositories.AccountRepository;
import wepa20.entities.Recommendation;
import wepa20.repositories.RecommendationRepository;
import wepa20.entities.Skill;
import wepa20.repositories.SkillRepository;

/**
 *
 * @author Elias Keski-Nisula
 */
@Service
@Transactional
public class ProfileBuilderService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private RecommendationRepository recommendationRepository;
    
    public Account getAccountById(Long accountid) {
        return accountRepository.getOne(accountid);
    }
    
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    
    public Skill getSkillById(Long skillid) {
        return skillRepository.getOne(skillid);
    }
    
    public void saveNewRecommendation(Skill skill, Recommendation recommendation) {
        skill.setRecommendationAmount(skill.getRecommendationAmount() + 1);
        recommendationRepository.save(recommendation);
    }

    public void saveNewSkill(Skill skill) {
        skillRepository.save(skill);
    }
    
    public Page<Skill> getTopSkills(Account currentprofileaccount) {
        Pageable p = PageRequest.of(0, 3, Sort.by("recommendationAmount").descending());
        return skillRepository.findAllByUser(currentprofileaccount, p);
    }
    
    public List<Skill> getSkillsOrdered(Account currentprofileaccount) {
        return skillRepository.findByUserOrderByRecommendationAmountDesc
        (currentprofileaccount);
    }
}
