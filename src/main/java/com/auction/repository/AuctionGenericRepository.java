package com.auction.repository;

import com.auction.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Helga on 03.11.17.
 */
@NoRepositoryBean
public interface AuctionGenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
