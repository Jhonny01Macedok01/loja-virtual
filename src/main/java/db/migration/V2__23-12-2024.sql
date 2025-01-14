-- Remover restrições da tabela `usuarios_acesso`, exceto a `unique_acesso_user`
ALTER TABLE public.usuarios_acesso
DROP CONSTRAINT IF EXISTS uk8bak9jswon2id2jbunuqlfl9e;

-- Remover restrição da tabela `nota_fiscal_venda`
ALTER TABLE public.nota_fiscal_venda
DROP CONSTRAINT IF EXISTS uklwo38kb0aaxboalphfjlan8qr;

-- Remover restrição da tabela `vd_cp_loja_virt`
ALTER TABLE public.vd_cp_loja_virt
DROP CONSTRAINT IF EXISTS ukhkxjejv08kldx994j4serhrbu;
