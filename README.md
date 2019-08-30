
### springboot2 时需要设置PasswordEncoder
```
 @Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser(userName).password(password).roles("ADMIN").and().passwordEncoder(new CustomPasswordEncoder());
}

// springboot2
static class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
```