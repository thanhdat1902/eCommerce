package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getCredentialByUserId(int userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credential credential);

    @Update("UPDATE CREDENTIALS set url=#{url}, username=#{username}, key=#{key}, password=#{password} where credentialid=#{credentialid}")
    int updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialid}")
    int deleteCredential(int credentialid);

    @Select("SELECT  * FROM CREDENTIALS WHERE credentialid=#{credentialid}")
    Credential getCredential(int credentialid);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid} AND username=#{credential.username}")
    Credential getCredentialByUsername(int userid, Credential credential);
}

