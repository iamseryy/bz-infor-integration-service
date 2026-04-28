SELECT  zbwmd550.t_idbx AS ?,
        zbwmd550.t_owar AS ?,
        zbwmd550.t_dsca AS ?,
        zbwmd551.t_stat AS ?,
        zbwmd551.t_cwar AS ?,
        zbwmd551.t_loca AS ?,
        zbwmd551.t_logn AS ?,
        zbwmd551.t_udat AS ?,
        zbwmd555.t_sern AS ?,
        zbwmd555.t_orno AS ?,
        zbwmd555.t_sern AS ?,
        zbwmd555.t_sern AS ?,
        zbwmd555.t_sern AS ?,
        zbwmd555.t_item AS ?,
        tcibd001.t_cuni AS ?,
        tcibd001.t_dsca AS ?,
        zbwmd555.t_clot AS ?,
        zbwmd555.t_quan AS ?,
        zbwmd555.t_cwar AS ?,
        zbwmd555.t_loca AS ?
FROM    ? AS zbwmd550
            LEFT JOIN ? AS zbwmd551 ON zbwmd550.t_idbx = zbwmd550.t_idbx
            LEFT JOIN ? AS zbwmd555 ON zbwmd550.t_idbx = zbwmd555.t_idbx
            LEFT JOIN ? AS tcibd001 ON zbwmd550.t_item = tcibd001.t_item
            LEFT JOIN ? AS tcmcs001 ON tcibd001.t_cuni = tcmcs001.t_cuni
WHERE   zbwmd550.t_idbx = ?