namespace WP6_055.Services
{
    public static class TextTrimmer
    {
        public static string Trim(string? input) =>
            input is null ? string.Empty : input.Trim();

        public static string Trim(string? input, params char[] trimChars) =>
            input is null ? string.Empty :
            (trimChars is { Length: > 0 } ? input.Trim(trimChars) : input.Trim());
    }
}
