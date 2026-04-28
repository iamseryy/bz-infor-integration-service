package ru.bz.bzinforintegrationservice.infrastructure.persistence.mssql.dao.impl.mechanicalpart.sqlprovider

import org.springframework.core.env.Environment
import org.springframework.stereotype.Component


private const val COMPANY_PROPERTY = "application.infor_company"

@Component
class MechanicalpartSqlProvider(
    private val environment: Environment
) {
    private val company = environment.getProperty(COMPANY_PROPERTY)


    fun setReportQuantityNamedSql(): String =
        """
            DECLARE @userLoginValue NVARCHAR(20);
            DECLARE @rowsAffected INT = 0;
            DECLARE @lineNumber INT;
            DECLARE @parentActivityCode NVARCHAR(30);
            DECLARE @childActivityCode NVARCHAR(30);
            DECLARE @existsError INT = 0;
            DECLARE @laborQuantity FLOAT = 0;


            SELECT TOP(1) @userLoginValue = tttaad200000.t_user
            FROM tttaad200000
            WHERE tttaad200000.t_uusr = :userLogin
            ;

            IF @userLoginValue IS NULL
            BEGIN
                SET @existsError = 1;
            END;

            IF @existsError = 0
            BEGIN
                IF :parentOperationCode = 0
                BEGIN 
                    SET @parentActivityCode = '';
                END
                ELSE
                BEGIN
                    SELECT TOP(1) @parentActivityCode = zbrrp504.t_tano
                    FROM tzbrrp504${company} AS zbrrp504
                    WHERE zbrrp504.t_cprj = :projectCode AND zbrrp504.t_cspa = :elementCode AND zbrrp504.t_ritm = :rootItemCode
                        AND zbrrp504.t_mitm = :parentItemCode AND zbrrp504.t_mpos = :parentItemPosition
                        AND zbrrp504.t_mopn = 0 
                        AND zbrrp504.t_opno = :parentOperationCode
                    ;
                END;
                
                IF @parentActivityCode IS NULL
                BEGIN
                    SET @existsError = 1;
                END;
            END;

            IF @existsError = 0
            BEGIN
                SELECT TOP(1) @childActivityCode = zbrrp504.t_tano, @laborQuantity = zbrrp504.t_tqua
                FROM tzbrrp504${company} AS zbrrp504
                WHERE zbrrp504.t_cprj = :projectCode AND zbrrp504.t_cspa = :elementCode AND zbrrp504.t_ritm = :rootItemCode
                    AND zbrrp504.t_mitm = :parentItemCode AND zbrrp504.t_mpos = :parentItemPosition
                    AND zbrrp504.t_mopn = :parentOperationCode
                    AND zbrrp504.t_opno = :childOperationCode
                ;
                 
                IF @childActivityCode IS NULL
                BEGIN
                    SET @existsError = 1;
                END;
            END;

            IF @existsError = 0
            BEGIN
                SELECT @lineNumber = MAX(zbppc700.t_sern)
                FROM tzbppc700${company} AS zbppc700
                WHERE zbppc700.t_cprj = :projectCode AND zbppc700.t_cspa = :elementCode AND zbppc700.t_ritm = :rootItemCode
                    AND zbppc700.t_item = :parentItemCode AND zbppc700.t_ipos = :parentItemPosition AND zbppc700.t_mact = @parentActivityCode
                    AND zbppc700.t_mapo = :parentOperationCode AND zbppc700.t_sact = @childActivityCode AND zbppc700.t_sapo = :childOperationCode
                ;
                
                IF @lineNumber IS NULL
                    SET @lineNumber = 0;

                SET @lineNumber = @lineNumber + 1;
                SET @laborQuantity = @laborQuantity * :reportedQuantity;

                INSERT INTO tzbppc700${company}(t_cprj, t_cspa, t_ritm, t_item, t_ipos, t_mact, t_mapo, t_sact, t_sapo, t_sern,
                t_requ, t_rlqu, t_logn, t_rdat, t_Refcntd, t_Refcntu)
                VALUES(:projectCode, :elementCode, :rootItemCode, :parentItemCode, :parentItemPosition,
                        @parentActivityCode, :parentOperationCode, @childActivityCode, :childOperationCode,
                        @lineNumber, :reportedQuantity, @laborQuantity, @userLoginValue, :reportedDateTime, 0, 0
                );
            END;
        """.trimIndent()
}
