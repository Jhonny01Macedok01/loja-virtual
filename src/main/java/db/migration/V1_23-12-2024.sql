select constraint_name from information_schema.constraint_column_usage
 where table_name = 'usuarios_acesso' and column_name = 'acesso_id'
 and constraint_name <> 'unique_acesso_user';
 
 alter table usuarios_acesso drop CONSTRAINT "uk8bak9jswon2id2jbunuqlfl9e";
 
 select constraint_name
FROM information_schema.table_constraints
WHERE table_name = 'nota_fiscal_venda';

ALTER TABLE public.nota_fiscal_venda 
DROP CONSTRAINT uklwo38kb0aaxboalphfjlan8qr;

SELECT constraint_name
FROM information_schema.table_constraints
WHERE table_name = 'vd_cp_loja_virt';

ALTER TABLE public.vd_cp_loja_virt 
DROP CONSTRAINT ukhkxjejv08kldx994j4serhrbu;
