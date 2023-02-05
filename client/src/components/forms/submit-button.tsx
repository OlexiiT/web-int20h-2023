type Props = {
  label?: string;
};

export default function SubmitButton({ label = "Submit" }: Props) {
  return <button>{label}</button>;
}
