package com.bubblewrap.bubble;

public enum EmojiEncrypter {
    A("( ^◡^)"),
    B("ʕっ•ᴥ•ʔっ"),
    C("(=ʘᆽʘ=)∫"),
    D("ʕっ•ᴥ•ʔっ"),
    E("(-ε- )"),
    F("(>▽<)"),
    G("(+_+)"),
    H("ヽ(•‿•)ノ"),
    I("(⚆ᗝ⚆)"),
    J("(・_・;)"),
    K("(~￣³￣)~"),
    L("(⊙△⊙)"),
    M("(☉_ ☉)"),
    N("(--_--)"),
    O("(⊙▂⊙)"),
    P("(￣ ￣|||)"),
    Q("（ ；¬＿¬)"),
    R("(^-^*)/"),
    S("(￣▽￣)ノ"),
    T("(ㆆ ᴗ ㆆ)"),
    U("(ツ)"),
    V("(•‿•)"),
    W("(ΦωΦ)"),
    X("(~_^)"),
    Y("(⌐▀͡ ̯ʖ▀)"),
    Z("(_ _ ) Zzz z");

    private final String code;

    EmojiEncrypter(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
