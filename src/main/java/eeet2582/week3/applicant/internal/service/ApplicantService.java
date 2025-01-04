package eeet2582.week3.applicant.internal.service;


import java.util.*;
import java.util.stream.Collectors;

import eeet2582.week3.applicant.internal.entity.Applicant;
import eeet2582.week3.applicant.external.ExternalApplicantInterface;
import eeet2582.week3.applicant.internal.InternalApplicantInterface;
import eeet2582.week3.applicant.internal.dtos.CreateApplicantDTO;
import eeet2582.week3.applicant.internal.dtos.InternalApplicantDTO;
import eeet2582.week3.applicant.internal.repository.ApplicantRepository;
import eeet2582.week3.helper.JedisHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
class ApplicantService implements InternalApplicantInterface, ExternalApplicantInterface {

    @Autowired
    private ApplicantRepository destRepository;

    @Autowired
    private JedisHelper applicantJedisHelper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String APPLICANT_PREFIX = "applicant";
    private static final String APPLICANT_KEY_ID_PREFIX = APPLICANT_PREFIX + ":key:";
    private static final String APPLICANT_NAME_INDEX = APPLICANT_PREFIX + ":name";

//    private static final String CUSTOMER_EMAIL_PREFIX = CUSTOMER_PREFIX + ":email";
    private static final String APPLICANT_ID_PREFIX = APPLICANT_PREFIX + ":id";

    public InternalApplicantDTO createApplicant(CreateApplicantDTO applicant) {

        Applicant savedApplicant = destRepository.save(new Applicant(applicant.getName(), applicant.getAge(), applicant.getCulturalClasses()));

        // Retrieving Applicant By ID using Key
        applicantJedisHelper.createRedisString (
                APPLICANT_KEY_ID_PREFIX + savedApplicant.getId(),
                savedApplicant
        );
        System.out.println("save to redis");
        // Retrieving Applicant by Name using HASH
        applicantJedisHelper.createHash (
                APPLICANT_NAME_INDEX,
                savedApplicant.getName(),
                savedApplicant
        );

        // For Retrieving Applicant By ID using Hash
        applicantJedisHelper.createHash (
                APPLICANT_ID_PREFIX,
                savedApplicant.getId() + "",
                savedApplicant
        );

//        applicantJedisHelper.createSortedSet(
//                CUSTOMER_BALANCE_INDEX,
//                customer,
//                customer.getBalance()
//        );

        return new InternalApplicantDTO(savedApplicant);
    }

    public Page<InternalApplicantDTO> getAllApplicants(String keyword, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());

        // Fetch all keys from the hash
        Map<Object, Object> allKeys = applicantJedisHelper.getHashByKey(APPLICANT_NAME_INDEX);

        // Check if there are any keys
        if (allKeys == null || allKeys.isEmpty()) {
            return Page.empty(pageable); // Return an empty Page
        }

        List<Applicant> applicantList = new ArrayList<>();

        if (!keyword.isEmpty()) {
            System.out.println("Keyword: " + keyword);
            // Filter keys based on the keyword
            applicantList = allKeys.entrySet().stream()
                    .filter(entry -> entry.getKey().toString().toLowerCase().contains(keyword.toLowerCase()))
                    .map(entry -> (Applicant) entry.getValue())
                    .collect(Collectors.toList());
        } else {
            applicantList = allKeys.values().stream()
                    .map(o -> (Applicant) o)
                    .collect(Collectors.toList());
        }

        List<InternalApplicantDTO> applicantDTOList = applicantList.stream()
                .map(InternalApplicantDTO::new)
                .toList();

        // Pagination logic
        int start = pageNo * pageSize;
        int end = Math.min(start + pageSize, applicantDTOList.size());

        if (start >= applicantList.size()) {
            throw new IllegalArgumentException("Invalid page number");
        }

        List<InternalApplicantDTO> paginatedKeys = applicantDTOList.subList(start, end);

        return new PageImpl<>(paginatedKeys, pageable, applicantList.size());

//        Set<String> redisKeys = redisTemplate.keys(APPLICANT_KEY_ID_PREFIX + "*");
//
//        List<InternalApplicantDTO> customerList = new ArrayList<>();
//
//        if (redisKeys != null && redisKeys.size() > 0) {
//            // Store the keys in a List
//            Iterator<String> iterator = redisKeys.iterator();
//
//            while (iterator.hasNext()) {
//                String keyStr = iterator.next();
//
//                Applicant customer = (Applicant) redisTemplate
//                        .opsForValue()
//                        .get(keyStr);
//
//                customerList.add(new InternalApplicantDTO(customer));
//            }
//        }
//
//        if (customerList.isEmpty()) {
//            customerList = destRepository.findAll().stream()
//                    .map(InternalApplicantDTO::new)
//                    .collect(Collectors.toList());
//        }
//
//        int start = pageNo * pageSize;
//        int end = Math.min(start + pageSize, customerList.size());
//
//        if (start >= customerList.size()) {
//            throw new IllegalArgumentException("Invalid page number");
//        }
//
//        List<InternalApplicantDTO> paginatedList = customerList.subList(start, end);
//
//        return new PageImpl<>(paginatedList, pageable, customerList.size());
    }

    public Optional<InternalApplicantDTO> updateApplicant(InternalApplicantDTO customerData) {
        Applicant existingApplicant = 
            destRepository.findById(customerData.getId()).get();

        if (existingApplicant != null) {
            existingApplicant.setAge(customerData.getAge());
            existingApplicant.setCulturalClasses(customerData.getCulturalClasses());
            existingApplicant.setName(customerData.getName());

            // Update the string key for ID
            applicantJedisHelper.createRedisString(
                    APPLICANT_KEY_ID_PREFIX + existingApplicant.getId(),
                    existingApplicant
            );

            // Update the hash for name (if name has changed)
            if (!existingApplicant.getName().equals(existingApplicant.getName())) {
                applicantJedisHelper.deleteHash(APPLICANT_NAME_INDEX, existingApplicant.getName());
            }
            applicantJedisHelper.createHash(
                    APPLICANT_NAME_INDEX,
                    existingApplicant.getName(),
                    existingApplicant
            );

            // Update the hash for ID
            applicantJedisHelper.createHash(
                    APPLICANT_ID_PREFIX,
                    String.valueOf(existingApplicant.getId()),
                    existingApplicant
            );

            return Optional.of(new InternalApplicantDTO(destRepository.save(existingApplicant)));
        }
           
        return Optional.empty();
        
    }

    public Optional<InternalApplicantDTO> deleteApplicant(Long id) {
        Applicant existingApplicant = destRepository.findById(id).get();

        if (existingApplicant != null) {
            // Remove associated cache entries
            applicantJedisHelper.deleteHash(APPLICANT_NAME_INDEX, existingApplicant.getName());
            applicantJedisHelper.deleteHash(APPLICANT_ID_PREFIX, String.valueOf(id));
            applicantJedisHelper.deleteKey(APPLICANT_KEY_ID_PREFIX + id);
            destRepository.delete(existingApplicant);
            return Optional.of(new InternalApplicantDTO(existingApplicant));
        }

        return Optional.empty();
    }

}
