package com.orgname.skeleton.mapper;

import com.orgname.skeleton.domain.Skeleton;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface SkeletonMapper {

    @Select("SELECT name FROM skeletons LIMIT 1")
    String getSingleString();

    @Insert("INSERT INTO SKELETONS (NAME) VALUES (#{name})")
    void createSkeleton(String name);

    @Select("SELECT * FROM SKELETONS WHERE NAME = #{name}")
    @ConstructorArgs(
            @Arg(column = "NAME", javaType = String.class)
    )
    Skeleton findByName(String name);
}
