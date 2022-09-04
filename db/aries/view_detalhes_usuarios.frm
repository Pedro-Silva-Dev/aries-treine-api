TYPE=VIEW
query=select `u`.`id` AS `id`,`u`.`nome` AS `nome`,`r`.`id` AS `regra_id`,`r`.`nome` AS `regra`,`u`.`ativo` AS `ativo` from (`aries`.`usuarios` `u` join `aries`.`regras` `r` on((`u`.`regra_id` = `r`.`id`)))
md5=e86a470249755b5b7435807097fa04fc
updatable=1
algorithm=0
definer_user=aries
definer_host=%
suid=2
with_check_option=0
timestamp=2022-09-03 23:41:41
create-version=1
source=SELECT u.id, u.nome, r.id AS regra_id, r.nome AS regra, u.ativo  \n	FROM usuarios u \n	JOIN regras r ON u.regra_id = r.id
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_general_ci
view_body_utf8=select `u`.`id` AS `id`,`u`.`nome` AS `nome`,`r`.`id` AS `regra_id`,`r`.`nome` AS `regra`,`u`.`ativo` AS `ativo` from (`aries`.`usuarios` `u` join `aries`.`regras` `r` on((`u`.`regra_id` = `r`.`id`)))
