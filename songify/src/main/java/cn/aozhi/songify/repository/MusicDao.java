package cn.aozhi.songify.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.aozhi.songify.entity.Music;

public interface MusicDao extends PagingAndSortingRepository<Music, Long>, JpaSpecificationExecutor<Music> {

}
