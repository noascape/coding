namespace WP6_058.Structs
{
    public readonly record struct OrderId(int Value)
    {
        public static implicit operator int(OrderId id) => id.Value;
        public static implicit operator OrderId(int value) => new(value);
        public override string ToString() => Value.ToString();
    }
}